// ============================================================
//  controllers.js — Controlador principal
// ============================================================

app.controller('MainCtrl', function($scope, CiudadService, RutaService, ParadaService, TipoService) {

    // ── Estado global ──────────────────────────────────────
    $scope.ciudades        = [];
    $scope.rutas           = [];
    $scope.paradas         = [];
    $scope.tipos           = [];
    $scope.ciudadSeleccionada = null;
    $scope.rutaSeleccionada   = null;

    $scope.nuevaRuta   = {};
    $scope.nuevaParada = {};
    $scope.modoEdicionRuta   = false;
    $scope.modoEdicionParada = false;
    $scope.mostrarFormRuta   = false;
    $scope.mostrarFormParada = false;

    $scope.mensaje = { texto: '', tipo: '' };

    // ── Inicialización ─────────────────────────────────────
    function init() {
        CiudadService.listar().then(function(res) {
            $scope.ciudades = res.data;
        });
        TipoService.listar().then(function(res) {
            $scope.tipos = res.data;
        });
    }

    function notificar(texto, tipo) {
        $scope.mensaje = { texto: texto, tipo: tipo || 'ok' };
        setTimeout(function() {
            $scope.$apply(function() { $scope.mensaje.texto = ''; });
        }, 3000);
    }

    // ══════════════════════════════════════════════════════
    //  CIUDADES
    // ══════════════════════════════════════════════════════
    $scope.seleccionarCiudad = function(ciudad) {
        $scope.ciudadSeleccionada = ciudad;
        $scope.rutaSeleccionada   = null;
        $scope.paradas            = [];
        $scope.mostrarFormRuta    = false;
        $scope.mostrarFormParada  = false;

        // Destruir mapa al cambiar de ciudad
        if ($scope.map) {
            $scope.map.remove();
            $scope.map = null;
            marcadores = [];
        }

        cargarRutas(ciudad.id);
    };

    function cargarRutas(idCiudad) {
        RutaService.porCiudad(idCiudad).then(function(res) {
            $scope.rutas = res.data;
        });
    }

    // ══════════════════════════════════════════════════════
    //  RUTAS
    // ══════════════════════════════════════════════════════
    $scope.seleccionarRuta = function(ruta) {
        $scope.rutaSeleccionada  = ruta;
        $scope.mostrarFormParada = false;
        $scope.paradas           = [];

        // Destruir mapa anterior si existe
        if ($scope.map) {
            $scope.map.remove();
            $scope.map = null;
            marcadores = [];
        }

        cargarParadas(ruta.id);
        dibujarMapa(ruta);
    };

    $scope.mostrarAgregarRuta = function() {
        $scope.modoEdicionRuta = false;
        $scope.nuevaRuta = { ciudad: { id: $scope.ciudadSeleccionada.id } };
        $scope.mostrarFormRuta = true;
    };

    $scope.editarRuta = function(ruta) {
        $scope.modoEdicionRuta = true;
        $scope.nuevaRuta = angular.copy(ruta);
        $scope.mostrarFormRuta = true;
    };

    $scope.guardarRuta = function() {
        var ruta = angular.copy($scope.nuevaRuta);
        ruta.ciudad = { id: $scope.ciudadSeleccionada.id };
        if (ruta.tipo && ruta.tipo.id) {
            ruta.tipo = { id: ruta.tipo.id };
        }

        var promesa = $scope.modoEdicionRuta
            ? RutaService.actualizar(ruta)
            : RutaService.crear(ruta);

        promesa.then(function() {
            $scope.mostrarFormRuta = false;
            $scope.nuevaRuta = {};
            cargarRutas($scope.ciudadSeleccionada.id);
            notificar($scope.modoEdicionRuta ? 'Ruta actualizada.' : 'Ruta creada.');
        }, function() {
            notificar('Error al guardar la ruta.', 'error');
        });
    };

    $scope.cancelarRuta = function() {
        $scope.mostrarFormRuta = false;
        $scope.nuevaRuta = {};
    };

    $scope.eliminarRuta = function(id) {
        if (!confirm('¿Eliminar esta ruta?')) return;
        RutaService.eliminar(id).then(function() {
            if ($scope.rutaSeleccionada && $scope.rutaSeleccionada.id === id) {
                $scope.rutaSeleccionada = null;
                $scope.paradas = [];
                if ($scope.map) {
                    $scope.map.remove();
                    $scope.map = null;
                    marcadores = [];
                }
            }
            cargarRutas($scope.ciudadSeleccionada.id);
            notificar('Ruta eliminada.');
        }, function() {
            notificar('Error al eliminar la ruta.', 'error');
        });
    };

    // ══════════════════════════════════════════════════════
    //  PARADAS
    // ══════════════════════════════════════════════════════
    function cargarParadas(idRuta) {
        ParadaService.porRuta(idRuta).then(function(res) {
            $scope.paradas = res.data;
            // Actualizar marcadores si el mapa ya existe
            if ($scope.map) {
                actualizarMarcadores($scope.paradas);
            }
        });
    }

    $scope.mostrarAgregarParada = function() {
        $scope.modoEdicionParada = false;
        $scope.nuevaParada = { ruta: { id: $scope.rutaSeleccionada.id } };
        $scope.mostrarFormParada = true;
    };

    $scope.editarParada = function(parada) {
        $scope.modoEdicionParada = true;
        $scope.nuevaParada = angular.copy(parada);
        $scope.mostrarFormParada = true;
    };

    $scope.guardarParada = function() {
        var parada = angular.copy($scope.nuevaParada);
        parada.ruta = { id: $scope.rutaSeleccionada.id };

        var promesa = $scope.modoEdicionParada
            ? ParadaService.actualizar(parada)
            : ParadaService.crear(parada);

        promesa.then(function() {
            $scope.mostrarFormParada = false;
            $scope.nuevaParada = {};
            cargarParadas($scope.rutaSeleccionada.id);
            notificar($scope.modoEdicionParada ? 'Parada actualizada.' : 'Parada creada.');
        }, function() {
            notificar('Error al guardar la parada.', 'error');
        });
    };

    $scope.cancelarParada = function() {
        $scope.mostrarFormParada = false;
        $scope.nuevaParada = {};
    };

    $scope.eliminarParada = function(id) {
        if (!confirm('¿Eliminar esta parada?')) return;
        ParadaService.eliminar(id).then(function() {
            cargarParadas($scope.rutaSeleccionada.id);
            notificar('Parada eliminada.');
        }, function() {
            notificar('Error al eliminar la parada.', 'error');
        });
    };

    // ══════════════════════════════════════════════════════
    //  MAPA (Leaflet) — con fix de visibilidad
    // ══════════════════════════════════════════════════════
    var marcadores = [];

    function dibujarMapa(ruta) {
        var lat = ruta.ciudad && ruta.ciudad.latitud  ? ruta.ciudad.latitud  : 4.6;
        var lng = ruta.ciudad && ruta.ciudad.longitud ? ruta.ciudad.longitud : -74.1;

        // Esperar a que Angular renderice el div #mapa antes de inicializar Leaflet
        setTimeout(function() {
            var contenedor = document.getElementById('mapa');
            if (!contenedor) return;

            $scope.map = L.map('mapa').setView([lat, lng], 13);

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '© OpenStreetMap contributors'
            }).addTo($scope.map);

            // Forzar recalculo de tamaño
            $scope.map.invalidateSize();

            // Si ya cargaron las paradas, pintar marcadores
            if ($scope.paradas && $scope.paradas.length > 0) {
                actualizarMarcadores($scope.paradas);
            }
        }, 400);
    }

    function actualizarMarcadores(paradas) {
        // Limpiar marcadores anteriores
        marcadores.forEach(function(m) { $scope.map.removeLayer(m); });
        marcadores = [];

        paradas.forEach(function(p, i) {
            if (p.latitud && p.longitud) {
                var m = L.marker([p.latitud, p.longitud])
                    .bindPopup(
                        '<b>' + (i + 1) + '. ' + p.nombre + '</b><br>' +
                        (p.descripcion || '') + '<br>⏱ ' + (p.tiempo || 0) + ' min'
                    )
                    .addTo($scope.map);
                marcadores.push(m);
            }
        });

        // Ajustar vista para mostrar todos los marcadores
        if (marcadores.length > 0) {
            var group = L.featureGroup(marcadores);
            $scope.map.fitBounds(group.getBounds().pad(0.2));
        }
    }

    // ── Arrancar ───────────────────────────────────────────
    init();
});