package wooteco.subway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import wooteco.subway.dao.StationDao;
import wooteco.subway.domain.Station;

@JdbcTest
class StationServiceTest {
    private static final Station station = new Station("강남역");
    private StationService stationService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        StationDao stationDao = new StationDao(jdbcTemplate);
        stationService = new StationService(stationDao);
    }

    @DisplayName("지하철역을 저장한다.")
    @Test
    void save() {
        stationService.save(station);
    }

    @DisplayName("이미 있는 이름의 지하철 역을 저장할 수 없다.")
    @Test
    void save_error() {
        //given
        stationService.save(station);

        //when then
        assertThatThrownBy(() -> stationService.save(station))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 해당 이름의 역이 있습니다.");
    }

    @DisplayName("지하철역을 조회한다.")
    @Test
    void findAll() {
        //given
        Station station2 = new Station("선릉역");
        stationService.save(station);
        stationService.save(station2);

        //when then
        assertThat(stationService.findAll())
                .containsOnly(station, station2);
    }

    @DisplayName("지하철역을 삭제한다.")
    @Test
    void delete() {
        //given
        Long id = stationService.save(station).getId();

        //when
        stationService.delete(id);

        //then
        assertThat(stationService.findAll())
                .isNotIn(station);
    }

    @DisplayName("없는 지하철역을 삭제할 수 없다.")
    @Test
    void delete_error() {
        assertThatThrownBy(() -> stationService.delete(100L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 아이디의 역이 없습니다.");
    }
}
