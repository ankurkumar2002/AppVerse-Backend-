package zipkin2.storage.cassandra;

import java.util.UUID;
import javax.annotation.processing.Generated;
import zipkin2.internal.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SelectTraceIdsFromServiceSpan_Input extends SelectTraceIdsFromServiceSpan.Input {

  private final String service;

  private final String span;

  private final int bucket;

  @Nullable
  private final Long start_duration;

  @Nullable
  private final Long end_duration;

  private final UUID start_ts;

  private final UUID end_ts;

  private final int limit_;

  AutoValue_SelectTraceIdsFromServiceSpan_Input(
      String service,
      String span,
      int bucket,
      @Nullable Long start_duration,
      @Nullable Long end_duration,
      UUID start_ts,
      UUID end_ts,
      int limit_) {
    if (service == null) {
      throw new NullPointerException("Null service");
    }
    this.service = service;
    if (span == null) {
      throw new NullPointerException("Null span");
    }
    this.span = span;
    this.bucket = bucket;
    this.start_duration = start_duration;
    this.end_duration = end_duration;
    if (start_ts == null) {
      throw new NullPointerException("Null start_ts");
    }
    this.start_ts = start_ts;
    if (end_ts == null) {
      throw new NullPointerException("Null end_ts");
    }
    this.end_ts = end_ts;
    this.limit_ = limit_;
  }

  @Override
  String service() {
    return service;
  }

  @Override
  String span() {
    return span;
  }

  @Override
  int bucket() {
    return bucket;
  }

  @Nullable
  @Override
  Long start_duration() {
    return start_duration;
  }

  @Nullable
  @Override
  Long end_duration() {
    return end_duration;
  }

  @Override
  UUID start_ts() {
    return start_ts;
  }

  @Override
  UUID end_ts() {
    return end_ts;
  }

  @Override
  int limit_() {
    return limit_;
  }

  @Override
  public String toString() {
    return "Input{"
        + "service=" + service + ", "
        + "span=" + span + ", "
        + "bucket=" + bucket + ", "
        + "start_duration=" + start_duration + ", "
        + "end_duration=" + end_duration + ", "
        + "start_ts=" + start_ts + ", "
        + "end_ts=" + end_ts + ", "
        + "limit_=" + limit_
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SelectTraceIdsFromServiceSpan.Input) {
      SelectTraceIdsFromServiceSpan.Input that = (SelectTraceIdsFromServiceSpan.Input) o;
      return this.service.equals(that.service())
          && this.span.equals(that.span())
          && this.bucket == that.bucket()
          && (this.start_duration == null ? that.start_duration() == null : this.start_duration.equals(that.start_duration()))
          && (this.end_duration == null ? that.end_duration() == null : this.end_duration.equals(that.end_duration()))
          && this.start_ts.equals(that.start_ts())
          && this.end_ts.equals(that.end_ts())
          && this.limit_ == that.limit_();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= service.hashCode();
    h$ *= 1000003;
    h$ ^= span.hashCode();
    h$ *= 1000003;
    h$ ^= bucket;
    h$ *= 1000003;
    h$ ^= (start_duration == null) ? 0 : start_duration.hashCode();
    h$ *= 1000003;
    h$ ^= (end_duration == null) ? 0 : end_duration.hashCode();
    h$ *= 1000003;
    h$ ^= start_ts.hashCode();
    h$ *= 1000003;
    h$ ^= end_ts.hashCode();
    h$ *= 1000003;
    h$ ^= limit_;
    return h$;
  }

}
