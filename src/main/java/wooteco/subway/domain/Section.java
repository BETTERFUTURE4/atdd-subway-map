package wooteco.subway.domain;

import wooteco.subway.exception.station.DuplicateStationException;

public class Section {

    private final Id id;
    private final Id lineId;
    private final Id upStationId;
    private final Id downStationId;
    private final Distance distance;

    public Section(final Long lineId, final Long upStationId, final Long downStationId, final int distance) {
        this(null, new Id(lineId), new Id(upStationId), new Id(downStationId), new Distance(distance));
    }
    public Section(final Long id, final Long lineId, final Long upStationId, final Long downStationId, final int distance) {
        this(new Id(id), new Id(lineId), new Id(upStationId), new Id(downStationId), new Distance(distance));
    }

    public Section(final Id id, final Id lineId, final Id upStatinoId, final Id downStationId,
        final Distance distance) {
        this.id = id;
        this.lineId = lineId;
        this.upStationId = upStatinoId;
        this.downStationId = downStationId;
        this.distance = distance;
        validateDuplicateStations(this.upStationId, this.downStationId);
    }

    private void validateDuplicateStations(final Id upStationId, final Id downStationId) {
        if (upStationId.equals(downStationId)) {
            throw new DuplicateStationException();
        }
    }

    public Long getId() {
        return id.getValue();
    }

    public Long getLineId() {
        return lineId.getValue();
    }

    public Long getUpStationId() {
        return upStationId.getValue();
    }

    public Long getDownStationId() {
        return downStationId.getValue();
    }

    public int getDistance() {
        return distance.getValue();
    }
}
