package YOURPACKAGEHERE;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;


/**
 * Created by metrical on 4/24/2017.
 * KVDatabase: Key-Value Database.
 * Using SharedPreferences to store all data types, as well as using Gson to serialize objects
 */

public class KVDatabase<T> implements ParameterizedType
{

    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private Type classType;

    public KVDatabase(Context context)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public <T> KVDatabase(Context context, Class<T[]> classType)
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.classType = classType;
    }

    //SET METHODS
    /**
     * @param value :   SharedPreferences Value of type Int
     * @param key   :   SharedPreferences Key
     */
    public void storeInt(int value, String key)
    {
        isNullKey(key);
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * @param value :   SharedPreferences Value of type String
     * @param key   :   SharedPreferences Key
     */

    public void storeString(String value, String key)
    {
        isNullKey(key);
        isNullValue(value);
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * @param value :   SharedPreferences Value of type Boolean
     * @param key   :   SharedPreferences Key
     */

    public void storeBool(boolean value, String key)
    {
        isNullKey(key);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * @param value :   SharedPreferences Value of type Float
     * @param key   :   SharedPreferences Key
     */

    public void storeFloat(float value, String key)
    {
        isNullKey(key);
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * @param value :   SharedPreferences Value of type Long
     * @param key   :   SharedPreferences Key
     */

    public void storeLong(long value, String key)
    {
        isNullKey(key);
        sharedPreferences.edit().putLong(key, value).apply();
    }


    //GET METHODS

    /**
     * @param key           :   SharedPreferences Key
     * for defaultValue     :   Will be 0 if key not found
     * @return              :   Returns value of key, or 0 if no key found
     */

    public int getInt(String key)
    {
        return sharedPreferences.getInt(key, 0);
    }
    /**
     * @param key           :   SharedPreferences Key
     * for defaultValue     :   Will be "" if key not found
     * @return              :   Returns value of key, or "" if no key found
     */

    public String getString(String key)
    {
        return sharedPreferences.getString(key, "");
    }
    /**
     * @param key           :   SharedPreferences Key
     * for defaultValue     :   Will be false if key not found
     * @return              :   Returns value of key, or false if no key found
     */

    public boolean getBool(String key)
    {
        return sharedPreferences.getBoolean(key, false);
    }
    /**
     * @param key           :   SharedPreferences Key
     * for defaultValue     :   Will be 0 if key not found
     * @return              :   Returns value of key, or 0 if no key found
     */

    public float getFloat(String key)
    {
        return sharedPreferences.getFloat(key, 0);
    }
    /**
     * @param key           :   SharedPreferences Key
     * for defaultValue     :   Will be 0 if key not found
     * @return              :   Returns value of key, or 0 if no key found
     */

    public long getLong(String key)
    {
        return sharedPreferences.getLong(key, 0);
    }

    public <T> void storeObject(T obj, String key)
    {
        isNullKey(key);
        isOverloaded();
        if (((Class) classType).isInstance(obj))
        {
            String jsonObject = gson.toJson(obj);
            storeString(jsonObject, key);
        }
        else throw new NullPointerException();
    }

    public T getObject(String key)
    {
        isOverloaded();
        String jsonObject = getString(key);

        return gson.fromJson(jsonObject, classType);
    }

    public <T> void storeObjectsList(List<T> value, String key)
    {
        isOverloaded();
        isNullKey(key);
        String jsonObjs = gson.toJson(value);
        storeString(jsonObjs, key);
    }



    public <T> List<T> getObjectsList(String key)
    {
        isOverloaded();
        isNullKey(key);
        final T[] jsonToObject = gson.fromJson(getString(key), classType);

        return Arrays.asList(jsonToObject);

    }

    /**
     * Checks for key or value being null. If so, throw exception
     */

    private void isOverloaded()
    {
        if (this.classType == null)
            throw new NullPointerException();
    }


    private void isNullKey(String key)
    {
        if (key == null || key.trim().equals(""))
        {
            throw new NullPointerException();
        }
    }

    private void isNullValue(String value)
    {
        if (value == null || value.trim().equals(""))
        {
            throw  new NullPointerException();
        }
    }


    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] {classType};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
