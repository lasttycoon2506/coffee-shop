package coffeeshop.Entities.Coffee;


public enum Coffee{
    COFFEE1("Kona", "Kona's Fire", "Medium", 10.99, "Ethiopia", 14),
    COFFEE2("Jimmy's", "Jimmy's Best", "Dark", 8.99, "Peru", 18),
    COFFEE3("Kiliminjaro", "KB Summit", "Light", 14.99, "Mozambique", 12),
    COFFEE4("Luna", "Luna's Special", "Light", 12.99, "Indonesia", 14),
    COFFEE5("Warber's", "Mid-day Blend", "Dark", 15.99, "Java", 16),
    COFFEE6("Yunnan", "Yunnan's Peak", "Medium", 11.99, "Yunnan", 14),
    COFFEE7("Locals", "Locallie", "Light", 11.99, "Yosemite", 14),
    COFFEE8("Helium", "Lift Me Up", "Dark", 15.99, "Mozambique", 16);
    
    private String brand;
    private String name;
    private String roast;
    private double price;
    private String region;
    private int size;

    private Coffee(String brand, String name, String roast, double price, String region, int size){
        this.brand = brand;
        this.name = name;
        this.roast = roast;
        this.price = price;
        this.region = region;
        this.size = size;
    }

}


