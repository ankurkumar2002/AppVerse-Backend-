package zipkin2.storage.cassandra;

import java.util.UUID;
import javax.annotation.processing.Generated;
import zipkin2.internal.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SelectTraceIdsFromSpan_Input extends SelectTraceIdsFromSpan.Input {

  @Nullable
  private final String l_service;

  @Nullable
  private final String annotation_query;

  private final UUID start_ts;

  private final UUID end_ts;

  private final int limit_;

  AutoValue_SelectTraceIdsFromSpan_Input(
      @Nullable String l_service,
      @Nullable String annotation_query,
      UUID start_ts,
      UUID end_ts,
      int limit_) {
    this.l_service = l_service;
    this.annotation_query = annotation_query;
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

  @Nullable
  @Override
  String l_service() {
    return l_service;
  }

  @Nullable
  @Override
  String annotation_query() {
    return annotation_query;
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
        + "l_service=" + l_service + ", "
        + "annotation_query=" + annotation_query + ", "
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
    if (o instanceof SelectTraceIdsFromSpan.Input) {
      SelectTraceIdsFromSpan.Input that = (SelectTraceIdsFromSpan.Input) o;
      return (this.l_service == null ? that.l_service() == null : this.l_service.equals(that.l_service()))
          && (this.annotation_query == null ? that.annotation_query() == null : this.annotation_query.equals(that.annotation_query()))
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
    h$ ^= (l_service == null) ? 0 : l_service.hashCode();
    h$ *= 1000003;
    h$ ^= (annotation_query == null) ? 0 : annotation_query.hashCode();
    h$ *= 1000003;
    h$ ^= start_ts.hashCode();
    h$ *= 1000003;
    h$ ^= end_ts.hashCode();
    h$ *= 1000003;
    h$ ^= limit_;
    return h$;
  }

}
