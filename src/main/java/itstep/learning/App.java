package itstep.learning;

import com.google.inject.Guice;
import itstep.learning.Async.AsyncDemo;
import itstep.learning.Async.MultitreadDemo;
import itstep.learning.db.DbDemo;
import itstep.learning.db.DbStartLogger;
import itstep.learning.fs.FileDemo;
import itstep.learning.ioc.DbModule;
import itstep.learning.ioc.IocDemo;
import itstep.learning.ioc.ServicesModule;
import itstep.learning.oop.Shop;
import itstep.learning.services.hash.Md5HashService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        //Shop shop = new Shop();
        //shop.run();


// Асинхронна робота
//        AsyncDemo asyncDemo = new AsyncDemo();
//        asyncDemo.threadDemo();

        MultitreadDemo multitreadDemo = new MultitreadDemo();
        multitreadDemo.runMultithreaded();


        // new Basics().run();
        // new Shop().run();
        // new IocDemo( new Md5HashService() ).run();
        //////////////////////////////////////

//      Guice
//           .createInjector(                   // Configuration
//                   new ServicesModule(),      // Модулів може бути декілька,
//                  new DbModule()             // розділяють за задачами
//         )
//              //.getInstance( IocDemo.class )             // Resolve
//        .getInstance( DbDemo.class )
//        //.getInstance( FileDemo.class )
//              //.getInstance( DbStartLogger.class )
//         .run();

//        com.google.inject.Injector injector = Guice.createInjector(
//                new ServicesModule(),
//                new DbModule()
//        );
//
//        if(args.length > 0 && args[0].equals("--journal")) {
//            injector.getInstance( DbStartLogger.class ).run();
//            injector.getInstance( DbStartLogger.class ).ShowAll();
//        }
//        else {
//            injector.getInstance( DbStartLogger.class ).run();
//        }


    }
}

/*
! Прив'язка до файлової системи
- package == назва папки
- class == назва файлу .java

IoC (inversion of control) - інверсія управління
{не плутати з DIP dependency inversion principle}
Управління життєвим циклом об'єктів (створенням, знищенням / перестворенням тощо)
 перекладається на спец. модуль - інжектор (контейнер служб).
Технічно, операції на кшталт "new" замінюються процедурою резолюції (resolve)
 об'єктів, яка відрізняється тим, що окрім створення об'єкту заповнює його залежності
У простішому випадку інжектор можна уявити як асоціативний масив
 [ type => instance ], який зберігає посилання на об'єкти відповідних типів та
 інжектує їх, аналізуючи тип точки інжекції

class Service {     interface     class Worker {
}                   IService         @Inject IService service;  <-----[IService => instance(Service)]
                                  }

new Worker -> incorrect ---- injector.getInstance(Worker) -> correct

У більш складному випадку - це вирішення графу залежностей, коли різні сервіси
залежать один від іншого.

Dependency Injection (DI) - @Inject (Marks)
Dependency Inversion Principle (DIP) - Not a class But interface
Inversion of Control (IoC) - Resolve (Graph)
 */