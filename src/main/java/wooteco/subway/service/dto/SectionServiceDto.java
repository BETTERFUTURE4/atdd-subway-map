package wooteco.subway.service.dto;

import wooteco.subway.domain.Section;

public class SectionServiceDto {

    private final Long id;
    private final Long lineId;
    private final Long upStationId;
    private final Long downStationId;
    private final int distance;

    public SectionServiceDto(Long lineId, Long upStationId, Long downStationId, int distance) {
        this(null, lineId, upStationId, downStationId, distance);
    }

    public SectionServiceDto(Long id, Long lineId, Long upStationId, Long downStationId,
        int distance) {
        this.id = id;
        this.lineId = lineId;
        this.upStationId = upStationId;
        this.downStationId = downStationId;
        this.distance = distance;
    }

    public static SectionServiceDto from(final Section section) {
        return new SectionServiceDto(
            section.getId(),
            section.getLineId(),
            section.getUpStationId(),
            section.getDownStationId(),
            section.getDistance()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getLineId() {
        return lineId;
    }

    public Long getUpStationId() {
        return upStationId;
    }

    public Long getDownStationId() {
        return downStationId;
    }

    public int getDistance() {
        return distance;
    }
}
