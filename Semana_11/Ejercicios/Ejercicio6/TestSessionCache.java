package Semana_11.Ejercicios.Ejercicio6;

public class TestSessionCache {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PRUEBA DEL EJERCICIO 6 INTEGRANDO TU CLASE HASHO ===");
        SessionCache cache = new SessionCache(5);

        // (1) Tres usuarios inician sesión
        cache.login("abc123", "Lucas_Admin", "ADMIN", 1000);   // 1 segundo de vida
        cache.login("xyz789", "Mateo_User", "USER", 5000);    // 5 segundos de vida
        cache.login("qwe456", "Sofia_Editor", "EDITOR", 8000); // 8 segundos de vida

        System.out.println("\n=== ESTADO INICIAL DEL CACHÉ EN TU HASHO ===");
        cache.mostrarCache();

        System.out.println("[SISTEMA] Esperando 1.5 segundos para forzar expiración...");
        Thread.sleep(1500);

        // (2) Validaciones
        System.out.println("\n--- (2) EJECUTANDO VALIDACIONES ---");
        cache.validate("abc123"); // Debe salir expirado
        cache.validate("xyz789"); // Debe salir válido

        // (3) Cierre de sesión explícito
        System.out.println("\n--- (3) EJECUTANDO LOGOUT EXPLÍCITO ---");
        cache.logout("xyz789"); // Llama al delete físico de tu HashO
        
        System.out.println("\n=== ESTADO POST-LOGOUT DEL CACHÉ ===");
        cache.mostrarCache();

        // (4) Limpieza
        System.out.println("\n--- (4) EJECUTANDO CLEAN EXPIRED ---");
        cache.cleanExpired();
    }
}