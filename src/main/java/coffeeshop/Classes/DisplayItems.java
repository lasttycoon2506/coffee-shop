package coffeeshop.Classes;

import coffeeshop.Entities.Coffee.Coffee;
import coffeeshop.Entities.Items.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class DisplayItems {
    private final ObjectProperty<Coffee> coffee = new SimpleObjectProperty<>();
    private final ObjectProperty<Item> item = new SimpleObjectProperty<>();
    
    public DisplayItems(Coffee coffee, Item item){
        this.coffee.set(coffee);
        this.item.set(item);
    }

    public Coffee getCoffee(){
        return coffee.get();
    }
    public Item getItem(){
        return item.get();
    }
}
