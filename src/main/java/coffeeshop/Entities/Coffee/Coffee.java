package coffeeshop.Entities.Coffee;


public class Coffee {
    public enum Brand {
        KONA, CARIBOU, JAVA, BANDED, WHOOPER
    }
    public enum Roast {
        LIGHT, MEDIUM, DARK 
    }
    public enum Price {
        P1(8.99), 
        P2(9.99), 
        P3(10.99), 
        P4(12.99), 
        P5(13.99);

        private final float price;
        Price(float price){
            this.price = price;
        }
    }

    public enum Region {
        INDONESIA, MALAYSIA, MOZAMBIQUE, ARGENTINA, ETHIOPIA
    }

    public enum Size {
        SMALL(12),
        MEDIUM(14), 
        LARGE(18);
        private final int size;
        Size(int size){
            this.size = size;
        }
    }

}
