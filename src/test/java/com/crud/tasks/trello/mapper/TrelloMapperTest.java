package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    private final TrelloCard testeeTrelloCard = new TrelloCard("Test Card", "Test description", "1", "1");
    private final TrelloCardDto testeeTrelloCardDto = new TrelloCardDto("Test CardDto", "Test description Dto", "2", "2");
    private final TrelloList testeeTrelloList = new TrelloList("1", "test_list", false);
    private final TrelloListDto testeeTrelloListDto = new TrelloListDto("2", "test_list_Dto", true);
    private final List<TrelloListDto> testeeTrelloListDtoList = Arrays.asList(testeeTrelloListDto);
    private final List<TrelloList> testeeTrelloListList = Arrays.asList(testeeTrelloList);
    private final TrelloBoard testeeTrelloBoard = new TrelloBoard("1", "test_board", testeeTrelloListList);
    private final TrelloBoardDto testeeTrelloBoardDto = new TrelloBoardDto("1", "test_boardDto", testeeTrelloListDtoList);
    private final List<TrelloBoardDto> testeeTrelloBoardDtoList = Arrays.asList(testeeTrelloBoardDto);
    private final List<TrelloBoard> testeeTrelloBoards = Arrays.asList(testeeTrelloBoard);

    @Test
    public void mapToBoards() {
        //Given & When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(testeeTrelloBoardDtoList);
        List<TrelloList> expectedList = trelloBoards.get(0).getLists();

        //Then
        assertThat(trelloBoards.size()).isEqualTo(1);
        assertThat(trelloBoards.get(0)).isEqualToComparingFieldByField(new TrelloBoard( "test_boardDto", "1", expectedList));
    }

    @Test
    public void mapToBoardsWhenBoardDtoIsNull() {
        //Given & When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(null);

        //Then
        assertThat(trelloBoards).isNull();
    }

    @Test
    public void mapToBoardsDto() {
        //Given & When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(testeeTrelloBoards);
        List<TrelloListDto> expectedListDto = trelloBoardDtos.get(0).getLists();

        //Then
        assertThat(trelloBoardDtos.size()).isEqualTo(1);
        assertThat(trelloBoardDtos.get(0)).isEqualToComparingFieldByField(new TrelloBoardDto( "1", "test_board",  expectedListDto));
    }

    @Test
    public void mapToBoardsDtoWhenBoardIsNull() {
        //Given & When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(null);

        //Then
        assertThat(trelloBoardDtos).isNull();
    }

    @Test
    public void mapToList() {
        //Given & When
        List<TrelloList> trelloListList = trelloMapper.mapToList(testeeTrelloListDtoList);

        //Then
        assertThat(trelloListList.size()).isEqualTo(1);
        assertThat(trelloListList.get(0)).isEqualToComparingFieldByField(new TrelloList("2", "test_list_Dto", true));
    }

    @Test
    public void mapToListWhenListDtoIsNull() {
        //Given & When
        List<TrelloList> trelloListList = trelloMapper.mapToList(null);

        //Then
        assertThat(trelloListList).isNull();
    }

    @Test
    public void mapToListDto() {
        //Given & When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(testeeTrelloListList);

        //Then
        assertThat(trelloListDtoList.size()).isEqualTo(1);
        assertThat(trelloListDtoList.get(0)).isEqualToComparingFieldByField(new TrelloListDto("1", "test_list", false));
    }

    @Test
    public void mapToListDtoWhenListIsNull() {
        //Given & When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(null);

        //Then
        assertThat(trelloListDtoList).isNull();
    }

    @Test
    public void mapToCardDto() {
        //Given & When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(testeeTrelloCard);

        //Then
        assertThat(trelloCardDto).isEqualToComparingFieldByField(new TrelloCardDto("Test Card", "Test description", "1", "1"));
    }

    @Test
    public void mapToCardDtoWhenCardIsNull() {
        //Given & When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(null);

        //Then
        assertThat(trelloCardDto).isNull();
    }

    @Test
    public void mapToCard() {
        //Given & When
        TrelloCard trelloCard = trelloMapper.mapToCard(testeeTrelloCardDto);

        //Then
        assertThat(trelloCard).isEqualToComparingFieldByField(new TrelloCard("Test CardDto", "Test description Dto", "2", "2"));
    }

    @Test
    public void mapToCardWhenCardDtoIsNull() {
        //Given & When
        TrelloCard trelloCard = trelloMapper.mapToCard(null);

        //Then
        assertThat(trelloCard).isNull();
    }
}