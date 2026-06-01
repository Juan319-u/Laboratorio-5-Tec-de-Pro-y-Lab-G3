// ============================================================
//  services.js — Servicios HTTP para la API
// ============================================================

app.service('CiudadService', function($http, API_URL) {
    var base = API_URL + '/ciudades/';
    this.listar  = function()   { return $http.get(base); };
    this.obtener = function(id) { return $http.get(base + id); };
    this.crear   = function(c)  { return $http.post(base, c); };
    this.actualizar = function(c) { return $http.put(base, c); };
    this.eliminar   = function(id){ return $http.delete(base + id); };
});

app.service('RutaService', function($http, API_URL) {
    var base = API_URL + '/rutas/';
    this.listar         = function()         { return $http.get(base); };
    this.porCiudad      = function(idCiudad) { return $http.get(base + 'ciudad/' + idCiudad); };
    this.obtener        = function(id)       { return $http.get(base + id); };
    this.crear          = function(r)        { return $http.post(base, r); };
    this.actualizar     = function(r)        { return $http.put(base, r); };
    this.eliminar       = function(id)       { return $http.delete(base + id); };
});

app.service('ParadaService', function($http, API_URL) {
    var base = API_URL + '/paradas/';
    this.porRuta    = function(idRuta) { return $http.get(base + 'ruta/' + idRuta); };
    this.obtener    = function(id)     { return $http.get(base + id); };
    this.crear      = function(p)      { return $http.post(base, p); };
    this.actualizar = function(p)      { return $http.put(base, p); };
    this.eliminar   = function(id)     { return $http.delete(base + id); };
});

app.service('TipoService', function($http, API_URL) {
    var base = API_URL + '/tipos/';
    this.listar = function() { return $http.get(base); };
});
