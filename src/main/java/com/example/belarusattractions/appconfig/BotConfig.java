package com.example.belarusattractions.appconfig;

import com.example.belarusattractions.SettingsTelegramBot;
import com.example.belarusattractions.botApi.query.QueryAndAnswerUser;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
@Data
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;
    @Bean
    public SettingsTelegramBot myWizardTelegramBot(QueryAndAnswerUser queryAndAnswerUser) {
        SettingsTelegramBot settingsTelegramBot = new SettingsTelegramBot(new DefaultBotOptions(), queryAndAnswerUser);
        settingsTelegramBot.setBotUserName(botUserName);
        settingsTelegramBot.setBotToken(botToken);
        settingsTelegramBot.setWebHookPath(webHookPath);

        return settingsTelegramBot;
    }
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
