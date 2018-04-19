package com.kuteinykov.programm;

import com.kuteinykov.programm.dao.ContactDao;
import com.kuteinykov.programm.dao.impl.FileSystemContactDaoImpl;
import com.kuteinykov.programm.services.ContactService;
import com.kuteinykov.programm.services.impl.ContactServiceImpl;
import com.kuteinykov.programm.services.impl.FSContactServiceImpl;
import com.kuteinykov.programm.view.CmdLineService;
import com.kuteinykov.programm.view.impl.CmdLineServiceImpl;

import java.io.IOException;

public class App {

    /**
     * Начало программы. Запускается программа,
     * создаются все сервиса и устанавливаются связи между ними.
     */

    public static void main(String[] args) throws IOException {

        //Создание самого нижнего слоя сервисов  - слой DAO
        // который работает со средствами долгосрочноого хранения информации.
        ContactDao contactDao = new FileSystemContactDaoImpl();

        //Создание слоя срвисов, которые хранят бизнесс логику. Логику управления моделями и т.д.
        //Обычно эти сервисы используют слой DAO для долгосрочного хранения данных.
        ContactService contactService = new FSContactServiceImpl(contactDao);

        //Создание сервисов слоя представления. Самые высокоуровневые сервиса которые управляют сервисами бизнесс логики.
        //Слой отвечающий за графический интерфейс и удобство работы Пользователя с программой
//        CmdLineService cmd = new CmdLineServiceImpl(new ContactServiceImpl());
        CmdLineService cmd = new CmdLineServiceImpl(new FSContactServiceImpl(contactDao));

        //Непосредственный запуск графического интерфейся и программы
        
        cmd.runMenu();

    }
}

