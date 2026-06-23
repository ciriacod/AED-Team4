package Semana_11.Ejercicios.Ejercicio6;

public class Session {
    String token;
    String username;
    String role;
    long expiresAt;

    public Session(String token, String username, String role, long expiresAt) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        long tiempoRestante = expiresAt - System.currentTimeMillis();
        return "(" + username + " [" + role + "], Expira en: " + (tiempoRestante > 0 ? tiempoRestante + "ms" : "EXPIRADO") + ")";
    }
}