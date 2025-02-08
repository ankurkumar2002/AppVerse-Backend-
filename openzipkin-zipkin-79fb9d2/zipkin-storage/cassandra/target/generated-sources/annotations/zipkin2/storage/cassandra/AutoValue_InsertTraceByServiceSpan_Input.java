package zipkin2.storage.cassandra;

import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_InsertTraceByServiceSpan_Input extends InsertTraceByServiceSpan.Input {

  private final String service;

  private final String span;

  private final int bucket;

  private final UUID ts;

  private final String trace_id;

  private final long duration;

  AutoValue_InsertTraceByServiceSpan_Input(
      String service,
      String span,
      int bucket,
      UUID ts,
      String trace_id,
      long duration) {
    if (service == null) {
      throw new NullPointerException("Null service");
    }
    this.service = service;
    if (span == null) {
      throw new NullPointerException("Null span");
    }
    this.span = span;
    this.bucket = bucket;
    if (ts == null) {
      throw new NullPointerException("Null ts");
    }
    this.ts = ts;
    if (trace_id == null) {
      throw new NullPointerException("Null trace_id");
    }
    this.trace_id = trace_id;
    this.duration = duration;
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

  @Override
  UUID ts() {
    return ts;
  }

  @Override
  String trace_id() {
    return trace_id;
  }

  @Override
  long duration() {
    return duration;
  }

  @Override
  public String toString() {
    return "Input{"
        + "service=" + service + ", "
        + "span=" + span + ", "
        + "bucket=" + bucket + ", "
        + "ts=" + ts + ", "
        + "trace_id=" + trace_id + ", "
        + "duration=" + duration
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof InsertTraceByServiceSpan.Input) {
      InsertTraceByServiceSpan.Input that = (InsertTraceByServiceSpan.Input) o;
      return this.service.equals(that.service())
          && this.span.equals(that.span())
          && this.bucket == that.bucket()
          && this.ts.equals(that.ts())
          && this.trace_id.equals(that.trace_id())
          && this.duration == that.duration();
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
    h$ ^= ts.hashCode();
    h$ *= 1000003;
    h$ ^= trace_id.hashCode();
    h$ *= 1000003;
    h$ ^= (int) ((duration >>> 32) ^ duration);
    return h$;
  }

}
