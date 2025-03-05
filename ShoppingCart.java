
import java.util.*;

// Item Structure
class Item{
    int itemId;
    String name;
    String description;
    int price;
    public Item(int  itemId,String name, String description,int price){
        this.itemId=itemId;
        this.name=name;
        this.description=description;
        this.price=price;
    }

   
 
}

class ShoppingCart{
    
    // Main Method
    public static void main(String args[]){

        //  Ref Value And Quantity
        HashMap<Item, Integer> cart=new HashMap<>();
        // ItemId And Item Ref Value
        HashMap< Integer, Item> itemList=new HashMap<>();

        int exit=1;
        while(exit!=0){

            // All Option Available Write Now
            System.out.println(itemList);
            System.out.println("Add To Cart -> 1");
            System.out.println("Show Quantity -> 2");
            System.out.println("Update Quantity -> 3");
            System.out.println("Delete Item -> 4");
            System.out.println("Display Cart Total Value -> 5");
            System.out.print("Enter The Number What You Want To Do : ");
            Scanner sc=new Scanner(System.in);
            int choice =sc.nextInt();

            // Add To Cart -> 1
            if(choice==1){
                System.out.print("Enter Product Name : ");
                String name=sc.next();

                System.out.print("Enter Item Id : ");
                int itemId=sc.nextInt();

                System.out.print("Enter Product Description : ");
                String description=sc.next();

                System.out.print("Enter Product Prise : ");
                int prise=sc.nextInt();

                System.out.print("Enter Quentity "+name+" : ");
                int quantity=sc.nextInt();

                if(!itemList.containsKey(itemId)){
                    Item item=new Item(itemId,name,description,prise);
                    itemList.put(itemId,item);
                    addToCart(cart,item,quantity);
                    System.out.println("Item Added Successfully");
                }
                else{
                    System.out.println("Iten Id is Is Already Stored Please Update item or Enter One Unique Id");
                }
                
           

            }

            // Show Quantity -> 2
            else if(choice==2){
                System.out.print("Enter Item Id : ");
                int id= sc.nextInt();
                int totalNoOfItem=displayQty(cart,itemList,id);

              

            }
            // Update Quantity -> 3
            else if(choice==3){

                System.out.print("Enter Item id : ");
                int itemId=sc.nextInt();

                System.out.print("Enter Quantity : ");
                int quantity=sc.nextInt();

                updateQty(cart,itemList,itemId,quantity);

            } 
            // Delete Item -> 4
            else if(choice==4){
                System.out.print("Enter Item Id : ");
                int itemId=sc.nextInt();

                if(itemList.containsKey(itemId)){
                    deleteItem(cart,itemList,itemId);
                }
                else{
                    System.out.println("Please Enter One Valid Item Id");
                }
          
            }
            // Display Cart Total Value -> 5
            else if(choice==5){
                double total Price=displayBill(cart);
                System.out.println("Total cart Price = "+total);

            }
            // Other Wise
            else{
                System.out.println("😓This Feature Comming Soon...........");
                System.out.println("Please Enter Number 1 or 2 or 3 or 4 or 5");
            }

            
            // Break From Loop
            System.out.print("If You Want To Break Break Press 0 Otherwise 1 :  ");
            exit =sc.nextInt();

            // Extra Security 
            if(exit==0){
                 System.out.print("Are You sure🥷 then Press Again 0 other wise 1 : ");
                 exit =sc.nextInt(); 
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
            System.out.println("This Item Is not Abilible Plese Enter This Item ");
        }
    }

    // Deleate Item Method
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
      

            System.out.println("Id"+"           "+"Name"+"           "+"Quantity"+"           "+"Price");
            System.out.println("------------------------------------------------------------------");
            for(Item item: cart.keySet()){
                totalPrice+=cart.get(item)*item.price;
                System.out.println(item.itemId+"           "+item.name+"           "+cart.get(item)+"             "+cart.get(item)*item.price);
            }
            System.out.println("-------------------------------------------------------------------");
 
           
        }
         return totalPrice;

    }


}


