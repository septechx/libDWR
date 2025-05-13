package com.siesque.libdwr.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class JSONStorage implements StorageProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final Path STORAGE_FILE;


    public JSONStorage(Path path) throws IOException {
        String workingDir = System.getProperty("user.dir");
        STORAGE_FILE = Path.of(workingDir).resolve(path);

        if (!Files.exists(STORAGE_FILE)) {
            Files.createDirectories(STORAGE_FILE.getParent());
            Files.createFile(STORAGE_FILE);
            saveData(new ArrayList<>());
        } else {
            if (Files.size(STORAGE_FILE) == 0) {
                saveData(new ArrayList<>());
            }
        }
    }

    @Override
    public <TData> void saveData(TData data) throws IOException {
        try (FileWriter writer = new FileWriter(STORAGE_FILE.toFile())) {
            GSON.toJson(data, writer);
        }
    }

    @Override
    public <TData> TData loadData(Type typeOfData) throws IOException {
        if (!STORAGE_FILE.toFile().exists()) {
            throw new RuntimeException("File not found: " + STORAGE_FILE);
        }

        try (FileReader reader = new FileReader(STORAGE_FILE.toFile())) {
            return GSON.fromJson(reader, typeOfData);
        }
    }
}
