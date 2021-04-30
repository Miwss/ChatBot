package ua.miws;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;

public class App{
	public final static void main(String[] args){
		String TOKEN = "1743035179:AAEMSGKQ34TmPSI-QmJL_4wvPMpZ0hG3Ohc";
		TelegramBot bot = new TelegramBot(TOKEN);

		//Создаем списаок ответов на вопросы
		String[] answerWordListHello = {"Привет", "Привет)", "Хай", "Хелоу", "ХаюХай", "Доров", "Хай)", "Доров)"};
		String[] answerWordListHowAreYou = {"Норм", "Нормально все", "Норм)", "Нормально)", "Нормально все)", "Все кул)", "Не жалуюсь)"};
		String[] answerWordListWhatAreYouDoing = {"Ничего)", "Залипаю просто)", "Сижу афк)", "Уже ничего)"};

		//создаем переменные с длиной массива со словами
		int Hello = answerWordListHello.length;
		int HowAreYou = answerWordListHowAreYou.length;
		int WhatAreYouDoing = answerWordListWhatAreYouDoing.length;

		bot.setUpdatesListener(updates -> {
			updates.forEach(System.out::println);

			updates.forEach(update -> {
				//Создаем переменные, которые хранят в себе рандомный индекс слова в массиве
				int randHello = (int) (Math.random() * Hello);
				int randHowAreYou = (int) (Math.random() * HowAreYou);
				int randWhatAreYouDoing = (int) (Math.random() * WhatAreYouDoing);

				//Присваиваем строковой переменной рандомное значение с массива(Можно убрать данный этап и написать его в выводе сообщения)
				String answHello = answerWordListHello[randHello];
				String answHowAreYou = answerWordListHowAreYou[randHowAreYou];
				String answWhatAreYouDoing = answerWordListWhatAreYouDoing[randWhatAreYouDoing];

				//Получаем сообщение от пользователя
				String mess = update.message().text();
				//Делаем проверку на входящее сообщение
				if(mess.equals("Привет") || mess.equals("Хай") || mess.equals("Хеллоу") || mess.equals("Доров") || mess.equals("привет")){
					bot.execute(new SendMessage(update.message().chat().id(), answHello));
				}
				else if(mess.equals("Как ты?")){
					bot.execute(new SendMessage(update.message().chat().id(),answHowAreYou));
				}
				else if(mess.equals("Что делаешь?")){
					bot.execute(new SendMessage(update.message().chat().id(), answWhatAreYouDoing));
				}
				//Доделать генирацию ответов*
				//Поискать как можно автоматизировать подбор ответов*
				//Обработчик ответов вне зависимости от вопроса!
			});



			return UpdatesListener.CONFIRMED_UPDATES_ALL;
		});
	}
}
