package zipkin2.storage.cassandra;

import java.util.UUID;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SelectTraceIdsFromServiceRemoteService_Input extends SelectTraceIdsFromServiceRemoteService.Input {

  private final String service;

  private final String remote_service;

  private final int bucket;

  private final UUID start_ts;

  private final UUID end_ts;

  private final int limit_;

  AutoValue_SelectTraceIdsFromServiceRemoteService_Input(
      String service,
      String remote_service,
      int bucket,
      UUID start_ts,
      UUID end_ts,
      int limit_) {
    if (service == null) {
      throw new NullPointerException("Null service");
    }
    this.service = service;
    if (remote_service == null) {
      throw new NullPointerException("Null remote_service");
    }
    this.remote_service = remote_service;
    this.bucket = bucket;
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
  String remote_service() {
    return remote_service;
  }

  @Override
  int bucket() {
    return bucket;
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
        + "remote_service=" + remote_service + ", "
        + "bucket=" + bucket + ", "
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
    if (o instanceof SelectTraceIdsFromServiceRemoteService.Input) {
      SelectTraceIdsFromServiceRemoteService.Input that = (SelectTraceIdsFromServiceRemoteService.Input) o;
      return this.service.equals(that.service())
          && this.remote_service.equals(that.remote_service())
          && this.bucket == that.bucket()
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
    h$ ^= remote_service.hashCode();
    h$ *= 1000003;
    h$ ^= bucket;
    h$ *= 1000003;
    h$ ^= start_ts.hashCode();
    h$ *= 1000003;
    h$ ^= end_ts.hashCode();
    h$ *= 1000003;
    h$ ^= limit_;
    return h$;
  }

}
