package com.siesque.libdwr.database;

import java.io.IOException;
import java.lang.reflect.Type;

public class Storage {
    private final StorageProvider PROVIDER;

    public Storage(StorageProvider provider) {
        this.PROVIDER = provider;
    }

    public <TData> void saveData(TData data) throws IOException {
        this.PROVIDER.saveData(data);
    };

    public <TData> TData loadData(Type typeOfData) throws IOException {
        return this.PROVIDER.loadData(typeOfData);
    };
}
