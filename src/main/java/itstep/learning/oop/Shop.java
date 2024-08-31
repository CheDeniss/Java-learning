package itstep.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> products;

    public Shop() {
        products = new ArrayList<Product>();
        products.add( new Lamp("Philips", 60.0) ) ;
        products.add( new Pump("Pumper", 100) ) ;
        products.add( new Accumulator("Varta", 80) );
    }

    public void run() {
        printProducts();
//        System.out.println("-----------------MANUAL----------------------");
//        printManualProducts();
//        System.out.println("-----------------NON-MANUAL----------------------");
//        printNonManualProducts();
//        System.out.println("-----------------WORKS----------------------");
//        showWorks();
    }

    private void printManualProducts() {
        for (Product product : products) {
            if( product instanceof Manual ) {
                System.out.println( product.getCard() );
            }
        }
    }

    private void printNonManualProducts() {
        for (Product product : products) {
            if( ! ( product instanceof Manual ) ) {
                System.out.println( product.getCard() );
            }
        }
    }

    private void printProducts() {
        for (Product product : products) {
            if(product instanceof Testable) {
                ((Testable) product).test();
            }
            else {
                System.out.println( product.getCard() );
            }
        }
    }

    private void showWorks() {
        for (Product product : products) {
            // Дістатись методу, що помічений анотацією @Works та виконати його
            for( Method method : product.getClass().getDeclaredMethods() ) {
                if( method.isAnnotationPresent( Works.class ) ) {

                    System.out.print( method.getAnnotation(Works.class).value() + " ");

                    method.setAccessible(true);   // даємо дозвіл на доступ
                    try {
                        method.invoke( product );   // product.method()
                    }
                    catch (IllegalAccessException | InvocationTargetException ex) {
                        System.err.println( ex.getMessage() );
                    }
                }
            }
        }
    }
}

/*                (manufacturer)
Shop <>------------> Product
                        △           | Поліморфізм - існування одної
                      / | \         | сутності в різних формах
           Accumulator  |  Pump
           (capacity)  Lamp  (productivity)
                      (power)

Accumulator (manufacturer, capacity)
Lamp (manufacturer, power)
Pump (manufacturer, productivity)

Product [manufacturer, getManufacturer(), set()]
Lamp extends Product
Lamp [ [<manufacturer>, getManufacturer(), set()], power, set() ]

Д.З. Ввести до переліку сутностей програми
Accumulator (manufacturer, capacity)
Додати до колекції Products об'єкт цієї сутності, переконатись у
виведенні її картки.

Інтерфейси - класи (типи) які містять тільки абстрактні тільки публічні тільки методи
- контракти
- маркери
= анотації (як у C# - атрибути)

Д.З. Додати анотацію Warranty("N") де N - кількість років гарантії
помітити даною анотацією класи-продукти, зазначивши різні числа
При виводі товарів додати відомості
а) про наявність гарантії
б) про термін гарантії
*/
