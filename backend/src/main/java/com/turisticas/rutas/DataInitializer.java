package com.turisticas.rutas;

import com.turisticas.rutas.model.*;
import com.turisticas.rutas.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TipoRepository tipoRepo;
    private final PaisRepository paisRepo;
    private final CiudadRepository ciudadRepo;
    private final RutaRepository rutaRepo;
    private final ParadaRepository paradaRepo;

    public DataInitializer(TipoRepository tipoRepo, PaisRepository paisRepo,
                           CiudadRepository ciudadRepo, RutaRepository rutaRepo,
                           ParadaRepository paradaRepo) {
        this.tipoRepo = tipoRepo;
        this.paisRepo = paisRepo;
        this.ciudadRepo = ciudadRepo;
        this.rutaRepo = rutaRepo;
        this.paradaRepo = paradaRepo;
    }

    @Override
    public void run(String... args) {

        // ── TIPOS ──────────────────────────────────────────────
        Tipo fluvial   = tipoRepo.save(new Tipo(null, "Fluvial"));
        Tipo terrestre = tipoRepo.save(new Tipo(null, "Terrestre"));
        Tipo aereo     = tipoRepo.save(new Tipo(null, "Aéreo"));
        Tipo maritimo  = tipoRepo.save(new Tipo(null, "Marítimo"));
        Tipo cultural  = tipoRepo.save(new Tipo(null, "Cultural"));

        // ── PAÍSES ─────────────────────────────────────────────
        Pais colombia  = paisRepo.save(new Pais(null, "Colombia",        "CO"));
        Pais francia   = paisRepo.save(new Pais(null, "Francia",         "FR"));
        Pais japon     = paisRepo.save(new Pais(null, "Japón",           "JP"));
        Pais espana    = paisRepo.save(new Pais(null, "España",          "ES"));
        Pais mexico    = paisRepo.save(new Pais(null, "México",          "MX"));
        Pais peru      = paisRepo.save(new Pais(null, "Perú",            "PE"));
        Pais egipto    = paisRepo.save(new Pais(null, "Egipto",          "EG"));
        Pais italia    = paisRepo.save(new Pais(null, "Italia",          "IT"));
        Pais eeuu      = paisRepo.save(new Pais(null, "Estados Unidos",  "US"));

        // ── CIUDADES ───────────────────────────────────────────
        Ciudad medellin    = ciudadRepo.save(new Ciudad(null, "Medellín",      colombia, -75.5636,  6.2518));
        Ciudad bogota      = ciudadRepo.save(new Ciudad(null, "Bogotá",        colombia, -74.0721,  4.7110));
        Ciudad paris       = ciudadRepo.save(new Ciudad(null, "París",         francia,   2.3522,  48.8566));
        Ciudad kioto       = ciudadRepo.save(new Ciudad(null, "Kioto",         japon,   135.7681,  35.0116));
        Ciudad tokio       = ciudadRepo.save(new Ciudad(null, "Tokio",         japon,   139.6917,  35.6895));
        Ciudad barcelona   = ciudadRepo.save(new Ciudad(null, "Barcelona",     espana,    2.1734,  41.3851));
        Ciudad madrid      = ciudadRepo.save(new Ciudad(null, "Madrid",        espana,   -3.7038,  40.4168));
        Ciudad cancun      = ciudadRepo.save(new Ciudad(null, "Cancún",        mexico,  -86.8515,  21.1619));
        Ciudad cdmx        = ciudadRepo.save(new Ciudad(null, "Ciudad de México", mexico, -99.1332, 19.4326));
        Ciudad cusco       = ciudadRepo.save(new Ciudad(null, "Cusco",         peru,    -71.9675, -13.5320));
        Ciudad elcairo     = ciudadRepo.save(new Ciudad(null, "El Cairo",      egipto,   31.2357,  30.0444));
        Ciudad roma        = ciudadRepo.save(new Ciudad(null, "Roma",          italia,   12.4964,  41.9028));
        Ciudad venecia     = ciudadRepo.save(new Ciudad(null, "Venecia",       italia,   12.3155,  45.4408));
        Ciudad nuevayork   = ciudadRepo.save(new Ciudad(null, "Nueva York",    eeuu,    -74.0060,  40.7128));

        // ── RUTAS ──────────────────────────────────────────────
        // París
        Ruta cruceroSena   = rutaRepo.save(new Ruta(null, "Crucero Histórico por el Sena",   fluvial,   paris,     "Recorrido emblemático por el río Sena"));
        Ruta museoParis    = rutaRepo.save(new Ruta(null, "Ruta de los Grandes Museos",       cultural,  paris,     "Louvre, Orsay y Centre Pompidou"));
        // Kioto
        Ruta templosKioto  = rutaRepo.save(new Ruta(null, "Ruta Templos de Kioto",           terrestre, kioto,     "Visita a los principales templos"));
        Ruta geishasKioto  = rutaRepo.save(new Ruta(null, "Distrito Gion y Geishas",          cultural,  kioto,     "Paseo por el barrio histórico de Gion"));
        // Tokio
        Ruta neontokio     = rutaRepo.save(new Ruta(null, "Tokio Neón: Shibuya y Shinjuku",  terrestre, tokio,     "Luces y modernidad de Tokio"));
        // Medellín
        Ruta culturalMed   = rutaRepo.save(new Ruta(null, "Circuito Cultural Medellín",      cultural,  medellin,  "Recorrido por los barrios históricos"));
        Ruta metrocable    = rutaRepo.save(new Ruta(null, "Metrocable y Comunas",             aereo,     medellin,  "Desde el centro hasta las laderas"));
        // Bogotá
        Ruta candelaria    = rutaRepo.save(new Ruta(null, "La Candelaria Histórica",          cultural,  bogota,    "Centro histórico y museos"));
        // Barcelona
        Ruta gaudiBarc     = rutaRepo.save(new Ruta(null, "Ruta Gaudí",                      cultural,  barcelona, "Sagrada Familia, Güell y Batlló"));
        Ruta goticBarc     = rutaRepo.save(new Ruta(null, "Barrio Gótico y Ramblas",          terrestre, barcelona, "Paseo por el casco antiguo"));
        // Roma
        Ruta foroRomano    = rutaRepo.save(new Ruta(null, "Foro Romano y Coliseo",            cultural,  roma,      "Corazón de la Roma antigua"));
        Ruta vaticano      = rutaRepo.save(new Ruta(null, "Vaticano y Museos",                cultural,  roma,      "Ciudad del Vaticano completa"));
        // Venecia
        Ruta gondola       = rutaRepo.save(new Ruta(null, "Paseo en Góndola por los Canales", fluvial,  venecia,   "Los canales más famosos de Venecia"));
        // Cusco
        Ruta incasCusco    = rutaRepo.save(new Ruta(null, "Camino Inca y Valle Sagrado",      terrestre, cusco,     "Ruta ancestral hacia Machu Picchu"));
        // El Cairo
        Ruta piramides     = rutaRepo.save(new Ruta(null, "Pirámides y Esfinge de Giza",      terrestre, elcairo,   "Las maravillas del mundo antiguo"));
        // Nueva York
        Ruta manhattan     = rutaRepo.save(new Ruta(null, "Manhattan Esencial",               terrestre, nuevayork, "Empire State, Central Park y más"));
        // Cancún
        Ruta caribeCancun  = rutaRepo.save(new Ruta(null, "Riviera Maya y Cenotes",           maritimo,  cancun,    "Costa caribeña y cenotes sagrados"));
        // Madrid
        Ruta pradoMadrid   = rutaRepo.save(new Ruta(null, "Triángulo del Arte",               cultural,  madrid,    "Prado, Reina Sofía y Thyssen"));

        // ── PARADAS ────────────────────────────────────────────

        // Crucero por el Sena
        paradaRepo.save(new Parada(null, "Embarcadero Torre Eiffel",       1, cruceroSena,  2.2945, 48.8584, 15, "Punto de inicio junto a la Torre"));
        paradaRepo.save(new Parada(null, "Muelle del Museo de Orsay",       2, cruceroSena,  2.3265, 48.8600, 20, "Frente a la antigua estación"));
        paradaRepo.save(new Parada(null, "Isla de la Cité - Notre Dame",    3, cruceroSena,  2.3499, 48.8530, 25, "La emblemática catedral gótica"));

        // Museos París
        paradaRepo.save(new Parada(null, "Museo del Louvre",                1, museoParis,   2.3376, 48.8606, 120, "El museo más visitado del mundo"));
        paradaRepo.save(new Parada(null, "Museo de Orsay",                  2, museoParis,   2.3265, 48.8600, 90,  "Arte impresionista siglos XIX-XX"));
        paradaRepo.save(new Parada(null, "Centre Pompidou",                 3, museoParis,   2.3522, 48.8607, 60,  "Arte moderno y contemporáneo"));

        // Templos Kioto
        paradaRepo.save(new Parada(null, "Templo Kinkaku-ji",               1, templosKioto, 135.7292, 35.0394, 60, "El famoso Pabellón Dorado"));
        paradaRepo.save(new Parada(null, "Santuario Fushimi Inari",         2, templosKioto, 135.7727, 34.9671, 90, "Miles de torii naranjas"));
        paradaRepo.save(new Parada(null, "Templo Ryoan-ji",                 3, templosKioto, 135.7178, 35.0345, 45, "Jardín zen de piedras"));

        // Gion Kioto
        paradaRepo.save(new Parada(null, "Calle Hanamikoji",                1, geishasKioto, 135.7752, 35.0037, 40, "La calle más famosa de Gion"));
        paradaRepo.save(new Parada(null, "Templo Yasaka",                   2, geishasKioto, 135.7785, 35.0036, 30, "Santuario al final de Shijo"));

        // Tokio Neón
        paradaRepo.save(new Parada(null, "Cruce de Shibuya",                1, neontokio,   139.7007, 35.6595, 30, "El cruce más famoso del mundo"));
        paradaRepo.save(new Parada(null, "Shinjuku de noche",               2, neontokio,   139.6917, 35.6938, 60, "Rascacielos y barrio del entretenimiento"));
        paradaRepo.save(new Parada(null, "Akihabara",                       3, neontokio,   139.7733, 35.7022, 45, "El paraíso del anime y tecnología"));

        // Circuito Cultural Medellín
        paradaRepo.save(new Parada(null, "Plaza Botero",                    1, culturalMed, -75.5675,  6.2513, 30, "Esculturas del maestro Botero"));
        paradaRepo.save(new Parada(null, "Museo de Antioquia",              2, culturalMed, -75.5670,  6.2519, 45, "Colección de arte antioqueño"));
        paradaRepo.save(new Parada(null, "Parque de las Luces",             3, culturalMed, -75.5660,  6.2505, 20, "Plaza de luces del centro"));
        paradaRepo.save(new Parada(null, "El Poblado",                      4, culturalMed, -75.5672,  6.2087, 60, "Barrio más moderno de Medellín"));

        // Metrocable
        paradaRepo.save(new Parada(null, "Estación Acevedo Metro",          1, metrocable,  -75.5588,  6.2936, 10, "Punto de transbordo al cable"));
        paradaRepo.save(new Parada(null, "Estación Andalucía",              2, metrocable,  -75.5543,  6.2984, 15, "Vista panorámica de la ciudad"));
        paradaRepo.save(new Parada(null, "Parque Arví",                     3, metrocable,  -75.5026,  6.2788, 90, "Reserva natural en las montañas"));

        // La Candelaria Bogotá
        paradaRepo.save(new Parada(null, "Museo del Oro",                   1, candelaria,  -74.0753,  4.7012, 60, "La colección de oro precolombino más grande"));
        paradaRepo.save(new Parada(null, "Plaza de Bolívar",                2, candelaria,  -74.0756,  4.5981, 20, "Corazón histórico de Bogotá"));
        paradaRepo.save(new Parada(null, "Monserrate",                      3, candelaria,  -74.0560,  4.6058, 90, "Cerro emblemático con vista a la ciudad"));

        // Gaudí Barcelona
        paradaRepo.save(new Parada(null, "Sagrada Família",                 1, gaudiBarc,    2.1744, 41.4036, 90, "La obra maestra inacabada de Gaudí"));
        paradaRepo.save(new Parada(null, "Park Güell",                      2, gaudiBarc,    2.1528, 41.4145, 60, "Mosaicos y vistas panorámicas"));
        paradaRepo.save(new Parada(null, "Casa Batlló",                     3, gaudiBarc,    2.1648, 41.3916, 45, "Fachada modernista en Passeig de Gràcia"));

        // Barrio Gótico Barcelona
        paradaRepo.save(new Parada(null, "Las Ramblas",                     1, goticBarc,    2.1725, 41.3797, 40, "El paseo más famoso de Barcelona"));
        paradaRepo.save(new Parada(null, "Catedral de Barcelona",           2, goticBarc,    2.1762, 41.3840, 30, "Catedral gótica del siglo XIII"));
        paradaRepo.save(new Parada(null, "Mercado de la Boqueria",          3, goticBarc,    2.1718, 41.3815, 45, "El mercado más colorido de España"));

        // Foro Romano y Coliseo
        paradaRepo.save(new Parada(null, "Coliseo Romano",                  1, foroRomano,  12.4922, 41.8902, 90, "El anfiteatro más grande del mundo antiguo"));
        paradaRepo.save(new Parada(null, "Foro Romano",                     2, foroRomano,  12.4853, 41.8925, 60, "Centro político de la Roma antigua"));
        paradaRepo.save(new Parada(null, "Palatino",                        3, foroRomano,  12.4877, 41.8891, 45, "La colina de los emperadores"));

        // Vaticano
        paradaRepo.save(new Parada(null, "Plaza de San Pedro",              1, vaticano,    12.4534, 41.9022, 30, "La plaza más grande del Vaticano"));
        paradaRepo.save(new Parada(null, "Basílica de San Pedro",           2, vaticano,    12.4534, 41.9022, 60, "La mayor iglesia del mundo"));
        paradaRepo.save(new Parada(null, "Museos Vaticanos",                3, vaticano,    12.4545, 41.9065, 120,"Capilla Sixtina y colecciones únicas"));

        // Góndola Venecia
        paradaRepo.save(new Parada(null, "Canal Grande",                    1, gondola,     12.3308, 45.4371, 30, "El canal principal de Venecia"));
        paradaRepo.save(new Parada(null, "Puente de Rialto",                2, gondola,     12.3359, 45.4380, 20, "El puente más antiguo del Canal Grande"));
        paradaRepo.save(new Parada(null, "Plaza San Marcos",                3, gondola,     12.3387, 45.4341, 60, "El corazón de Venecia"));

        // Camino Inca
        paradaRepo.save(new Parada(null, "Sacsayhuamán",                    1, incasCusco, -71.9819,-13.5083, 60, "Fortaleza inca sobre el Cusco"));
        paradaRepo.save(new Parada(null, "Valle Sagrado",                   2, incasCusco, -72.2647,-13.3167, 90, "El corazón del Imperio Inca"));
        paradaRepo.save(new Parada(null, "Aguas Calientes",                 3, incasCusco, -72.5271,-13.1538,120, "Pueblo al pie de Machu Picchu"));

        // Pirámides
        paradaRepo.save(new Parada(null, "Gran Pirámide de Keops",          1, piramides,   31.1342, 29.9792, 60, "La única maravilla antigua en pie"));
        paradaRepo.save(new Parada(null, "Esfinge de Giza",                 2, piramides,   31.1375, 29.9753, 30, "El guardián milenario del desierto"));
        paradaRepo.save(new Parada(null, "Museo Egipcio",                   3, piramides,   31.2336, 30.0478, 90, "La mayor colección de antigüedades egipcias"));

        // Manhattan
        paradaRepo.save(new Parada(null, "Empire State Building",           1, manhattan,  -73.9857, 40.7484, 60, "El rascacielos más icónico de NY"));
        paradaRepo.save(new Parada(null, "Central Park",                    2, manhattan,  -73.9654, 40.7829, 90, "El pulmón verde de Manhattan"));
        paradaRepo.save(new Parada(null, "Times Square",                    3, manhattan,  -73.9855, 40.7580, 30, "Las pantallas y el corazón de NY"));
        paradaRepo.save(new Parada(null, "Estatua de la Libertad",          4, manhattan,  -74.0445, 40.6892, 120,"Símbolo de libertad en la bahía"));

        // Riviera Maya
        paradaRepo.save(new Parada(null, "Chichén Itzá",                    1, caribeCancun,-88.5689, 20.6843, 120,"La ciudad maya más visitada del mundo"));
        paradaRepo.save(new Parada(null, "Cenote Ik-Kil",                   2, caribeCancun,-88.5718, 20.6728, 60, "Cenote sagrado junto a Chichén"));
        paradaRepo.save(new Parada(null, "Playa del Carmen",                3, caribeCancun,-87.0793, 20.6274, 90, "La quinta avenida y playa caribeña"));

        // Triángulo del Arte Madrid
        paradaRepo.save(new Parada(null, "Museo del Prado",                 1, pradoMadrid, -3.6920, 40.4138, 120,"La pinacoteca más importante de España"));
        paradaRepo.save(new Parada(null, "Museo Reina Sofía",               2, pradoMadrid, -3.6939, 40.4085, 90, "El Guernica de Picasso"));
        paradaRepo.save(new Parada(null, "Museo Thyssen-Bornemisza",        3, pradoMadrid, -3.6940, 40.4158, 90, "Arte desde el siglo XIII al XX"));

        System.out.println("✅ Datos de prueba cargados: 14 ciudades, 18 rutas, 60+ paradas.");
    }
}
