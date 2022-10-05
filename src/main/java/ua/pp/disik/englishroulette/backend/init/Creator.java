package ua.pp.disik.englishroulette.backend.init;

import org.springframework.boot.CommandLineRunner;

public abstract class Creator implements CommandLineRunner {
    @Override
    public void run(String... args) {
        boolean runnable = Boolean.parseBoolean(System.getProperty("creators"));
        if (runnable) {
            create();
        }
    }

    public abstract void create();
}
