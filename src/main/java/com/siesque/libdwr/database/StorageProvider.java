package com.siesque.libdwr.database;

import java.io.IOException;
import java.lang.reflect.Type;

public interface StorageProvider {
    public <TData> void saveData(TData data) throws IOException;
    public <TData> TData loadData(Type typeOfData) throws IOException;
}
