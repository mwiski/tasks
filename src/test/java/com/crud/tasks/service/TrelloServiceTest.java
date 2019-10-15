package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;

    private TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test description", "top", "1");
    private CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Test", "http://test.com");
    private Mail mail = new Mail("test@test.com", "Test", "Test Message", "");

    @Test
    public void createTrelloCardTest() {
        //Given
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When & Then
        assertThat(trelloService.createTrelloCard(trelloCardDto)).isEqualTo(createdTrelloCardDto);
    }
}