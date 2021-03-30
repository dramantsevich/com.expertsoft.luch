package util;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "TypeFilterProvider")
    public Object [] getTypeData(){
        Object[] data = {"Men’s", "Women’s"};

        return data;
    }

    @DataProvider(name = "MovementFilterProvider")
    public Object [] getMovementData(){
      Object[] data = {"Quartz", "Mechanical"};

      return data;
    }

    @DataProvider(name = "SortProvider")
    public Object [] getSortData(){
        Object[] data = {"First popular", "Price: lowest first"};

        return data;
    }
}
