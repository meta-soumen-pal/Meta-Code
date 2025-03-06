import java.util.Scanner;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


// Item Structure
class Item{
    int itemId;
    String name;
    String description;
    double price;
    public Item(int  itemId,String name, String description,double price){
        this.itemId=itemId;
        this.name=name;
        this.description=description;
        this.price=price;
    }

   
 
}

public class ShoppingCart{

    // Main Method
    public static void main(String args[]){

        //  Item Ref Value And Quantity
        HashMap<Item, Integer> cart=new HashMap<>();

        // ItemId And Item Ref Value
        HashMap< Integer, Item> itemList=new HashMap<>();

        int exit=1;
        Scanner sc=new Scanner(System.in);
        Scanner scInt=new Scanner(System.in);

        while(exit!=0){

            // All  available options sre listed below
            System.out.println("----------------All available items-----------------");
            for(Integer itemId: itemList.keySet()){
                Item item=itemList.get(itemId);
                System.out.println(itemId +" -> "+ item.name+" -> "+item.price);
            }
            System.out.println("----------------------------------------------------");
           

            System.out.println("---------All available actions listed Below---------");

            System.out.println("Add to cart press-> 1");
            System.out.println("Show quantity press -> 2");
            System.out.println("Update quantity press -> 3");
            System.out.println("Delete item press -> 4");
            System.out.println("Display Bill press -> 5");
            System.out.println("Exit -> 0 ");
            
            System.out.println("----------------------------------------------------");
            System.out.print("Enter the number corresponding to the action you want to perform : ");
            int choice =sc.nextInt();

            // Add To Cart -> 1
            if(choice==1){
                System.out.print("Enter item id : ");
                int itemId=sc.nextInt();

                System.out.print("Enter product price : ");
                double price=sc.nextInt();
                
                // Check if Value less Than 0
                if(price < 0){
                    System.out.print("Warning ⚠️...Enter quantity (Quantity Must Be Greater Than 0 ) : ");
                    price=scInt.nextInt();
                }

               

                System.out.print("Enter  product name (Dont Use space Betwen Word) : ");
                String name=sc.next();
                System.out.print("Enter product description (Dont Use space Betwen Word) : ");
                String description=sc.next();

                System.out.print("Enter quantity of "+name+"  : ");
                int quantity;
                
                while(true){
                    if(sc.hasNextInt()){
                        quantity=sc.nextInt();
                        break;
                    }
                    else{
                        System.out.print("Please enter an Integer : ");
                        sc.next();

                    }

                }
           



                // Check Value Greter Than 0
                if(quantity < 0 ){
                    System.out.print("Warning ⚠️...Enter quantity (Quantity Must Be Greter Than 0 ) : ");
                    quantity=sc.nextInt();
                }
                

                           
              
              

                // Check item id already present or not
                if(!itemList.containsKey(itemId)){
                    //Create a new item object and add it to the item list
                    Item item=new Item(itemId,name,description,price);
                    itemList.put(itemId,item);
                    // Call add to cart method
                    addToCart(cart,item,quantity);
                    System.out.println("Item Added Successfully");
                }
                // if item id already present it item list then show this
                else{
                    System.out.println(" Warning ⚠️: An Item with this ID is already stored. Please update the item or enter a unique ID");
                }
                
           

            }

            // Show Quantity -> 2
            else if(choice==2){
                System.out.print("Enter item id : ");
                int id= scInt.nextInt();
                int totalNoOfItem=displayQty(cart,itemList,id);
            }
            // Update Quantity -> 3
            else if(choice==3){
                System.out.print("Enter item id : ");
                int itemId=scInt.nextInt();
                System.out.print("Enter Quantity : ");
                int quantity;
                // Check Value Is Integer or Not
                while(true){
                    if(sc.hasNextInt()){
                        quantity=sc.nextInt();
                        break;
                    }
                    else{
                        System.out.print("Please enter an Integer : ");
                        sc.next();

                    }

                }
                if(quantity < 0){
                    System.out.print("Warning ⚠️...Enter quantity (Quantity Must Be Greter Than 0 ) : ");
                    quantity=scInt.nextInt();;
                }

                updateQty(cart,itemList,itemId,quantity);

            } 
            // Delete Item -> 4
            else if(choice==4){
                System.out.print("Enter Item Id : ");
                int itemId=scInt.nextInt();

                if(itemList.containsKey(itemId)){
                    deleteItem(cart,itemList,itemId);
                }
                else{
                    System.out.println("Please Enter One Valid Item Id");
                }
            }
            // Display Cart Total Value -> 5
            else if(choice==5){
                double totalPrice=displayBill(cart);
                System.out.println("Total cart price = "+ totalPrice);
            }
            // Other Wise
            else if (choice >5 || choice <0){
                System.out.println("😓This feature is comming soon...");
                System.out.println("Please enter number 1 or 2 or 3 or 4 or 5");
            }

            else{
                break;
            }
            // Break From Loop
            System.out.print("If You Want To exit, Press 0. Otherwise, press 1 :  ");
            exit =scInt.nextInt();

            // Extra Security 
            if(exit==0){
                 System.out.print("Are You sure? Press 0 again to confirm exit, Otherwise press 1 : ");
                 exit =scInt.nextInt(); 
            }   
        }  
    }

    // Add To Cart Method
    public static void addToCart(Map <Item,Integer> cart,Item item, Integer quantity ){   
        if(!cart.containsKey(item)){
            cart.put(item,quantity);
        }

    }

    // Display Quantity Method
    public static Integer displayQty(Map<Item,Integer>cart, Map<Integer, Item>itemList, int itemId){

        if(cart.isEmpty()){
            System.out.println("Cart Is Empty ");
            return 0;
        }
        if(!itemList.containsKey(itemId)){
            System.out.println("No Item Found");
            return 0;
        }
        Item item=itemList.get(itemId);
        System.out.println("Total Number of "+item.name+" = "+cart.get(item));
        return cart.get(item);
        
    }

    // Update Quantity Method
    public static void updateQty(Map<Item,Integer>cart,Map<Integer, Item>itemList, int itemId, Integer quantity){

        Item item=itemList.get(itemId);
        
        if(cart.containsKey(item)){
            cart.replace(item,cart.get(item)+quantity);
        }
        else{
            System.out.println("This Item Id Is not Available Please Enter one Valid Id");
        }
    }

    // Delete Item Method
    public static void deleteItem(Map<Item,Integer>cart,Map<Integer, Item>itemList, int itemId){

        Item item=itemList.get(itemId);
        
        if(cart.isEmpty() || !cart.containsKey(item)){
            System.out.println("Not Avialable");
            return;
        }
        else{
            System.out.println(item.name +" Deleted Successfully");
            cart.remove(item);
        }


    }

    // Display Bill Method
    public static double displayBill(Map<Item,Integer>cart){
        double totalPrice=0;
        if(cart.isEmpty()){
            return 0;

        }
        else{
            System.out.println("");
            System.out.println("------------Meta Mart------------------Thursday 6 Mar 2025------------");
            System.out.println("");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Id"+"           "+"Name"+"           "+"Quantity"+"           "+"Price");
            System.out.println("----------------------------------------------------------------------");
            for(Item item: cart.keySet()){
                totalPrice+=cart.get(item)*item.price;
                System.out.println(item.itemId+"          "+item.name+"          "+cart.get(item)+"             "+cart.get(item)*item.price);
            }
            System.out.println("----------------------------------------------------------------------");
 
           
        }
        return totalPrice;
    }
}


