package zipkin2.storage.cassandra;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.processing.Generated;
import zipkin2.Annotation;
import zipkin2.Endpoint;
import zipkin2.internal.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_InsertSpan_Input extends InsertSpan.Input {

  private final UUID ts_uuid;

  @Nullable
  private final String trace_id_high;

  private final String trace_id;

  @Nullable
  private final String parent_id;

  private final String id;

  @Nullable
  private final String kind;

  @Nullable
  private final String span;

  private final long ts;

  private final long duration;

  @Nullable
  private final Endpoint l_ep;

  @Nullable
  private final Endpoint r_ep;

  private final List<Annotation> annotations;

  private final Map<String, String> tags;

  @Nullable
  private final String annotation_query;

  private final boolean debug;

  private final boolean shared;

  AutoValue_InsertSpan_Input(
      UUID ts_uuid,
      @Nullable String trace_id_high,
      String trace_id,
      @Nullable String parent_id,
      String id,
      @Nullable String kind,
      @Nullable String span,
      long ts,
      long duration,
      @Nullable Endpoint l_ep,
      @Nullable Endpoint r_ep,
      List<Annotation> annotations,
      Map<String, String> tags,
      @Nullable String annotation_query,
      boolean debug,
      boolean shared) {
    if (ts_uuid == null) {
      throw new NullPointerException("Null ts_uuid");
    }
    this.ts_uuid = ts_uuid;
    this.trace_id_high = trace_id_high;
    if (trace_id == null) {
      throw new NullPointerException("Null trace_id");
    }
    this.trace_id = trace_id;
    this.parent_id = parent_id;
    if (id == null) {
      throw new NullPointerException("Null id");
    }
    this.id = id;
    this.kind = kind;
    this.span = span;
    this.ts = ts;
    this.duration = duration;
    this.l_ep = l_ep;
    this.r_ep = r_ep;
    if (annotations == null) {
      throw new NullPointerException("Null annotations");
    }
    this.annotations = annotations;
    if (tags == null) {
      throw new NullPointerException("Null tags");
    }
    this.tags = tags;
    this.annotation_query = annotation_query;
    this.debug = debug;
    this.shared = shared;
  }

  @Override
  UUID ts_uuid() {
    return ts_uuid;
  }

  @Nullable
  @Override
  String trace_id_high() {
    return trace_id_high;
  }

  @Override
  String trace_id() {
    return trace_id;
  }

  @Nullable
  @Override
  String parent_id() {
    return parent_id;
  }

  @Override
  String id() {
    return id;
  }

  @Nullable
  @Override
  String kind() {
    return kind;
  }

  @Nullable
  @Override
  String span() {
    return span;
  }

  @Override
  long ts() {
    return ts;
  }

  @Override
  long duration() {
    return duration;
  }

  @Nullable
  @Override
  Endpoint l_ep() {
    return l_ep;
  }

  @Nullable
  @Override
  Endpoint r_ep() {
    return r_ep;
  }

  @Override
  List<Annotation> annotations() {
    return annotations;
  }

  @Override
  Map<String, String> tags() {
    return tags;
  }

  @Nullable
  @Override
  String annotation_query() {
    return annotation_query;
  }

  @Override
  boolean debug() {
    return debug;
  }

  @Override
  boolean shared() {
    return shared;
  }

  @Override
  public String toString() {
    return "Input{"
        + "ts_uuid=" + ts_uuid + ", "
        + "trace_id_high=" + trace_id_high + ", "
        + "trace_id=" + trace_id + ", "
        + "parent_id=" + parent_id + ", "
        + "id=" + id + ", "
        + "kind=" + kind + ", "
        + "span=" + span + ", "
        + "ts=" + ts + ", "
        + "duration=" + duration + ", "
        + "l_ep=" + l_ep + ", "
        + "r_ep=" + r_ep + ", "
        + "annotations=" + annotations + ", "
        + "tags=" + tags + ", "
        + "annotation_query=" + annotation_query + ", "
        + "debug=" + debug + ", "
        + "shared=" + shared
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof InsertSpan.Input) {
      InsertSpan.Input that = (InsertSpan.Input) o;
      return this.ts_uuid.equals(that.ts_uuid())
          && (this.trace_id_high == null ? that.trace_id_high() == null : this.trace_id_high.equals(that.trace_id_high()))
          && this.trace_id.equals(that.trace_id())
          && (this.parent_id == null ? that.parent_id() == null : this.parent_id.equals(that.parent_id()))
          && this.id.equals(that.id())
          && (this.kind == null ? that.kind() == null : this.kind.equals(that.kind()))
          && (this.span == null ? that.span() == null : this.span.equals(that.span()))
          && this.ts == that.ts()
          && this.duration == that.duration()
          && (this.l_ep == null ? that.l_ep() == null : this.l_ep.equals(that.l_ep()))
          && (this.r_ep == null ? that.r_ep() == null : this.r_ep.equals(that.r_ep()))
          && this.annotations.equals(that.annotations())
          && this.tags.equals(that.tags())
          && (this.annotation_query == null ? that.annotation_query() == null : this.annotation_query.equals(that.annotation_query()))
          && this.debug == that.debug()
          && this.shared == that.shared();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= ts_uuid.hashCode();
    h$ *= 1000003;
    h$ ^= (trace_id_high == null) ? 0 : trace_id_high.hashCode();
    h$ *= 1000003;
    h$ ^= trace_id.hashCode();
    h$ *= 1000003;
    h$ ^= (parent_id == null) ? 0 : parent_id.hashCode();
    h$ *= 1000003;
    h$ ^= id.hashCode();
    h$ *= 1000003;
    h$ ^= (kind == null) ? 0 : kind.hashCode();
    h$ *= 1000003;
    h$ ^= (span == null) ? 0 : span.hashCode();
    h$ *= 1000003;
    h$ ^= (int) ((ts >>> 32) ^ ts);
    h$ *= 1000003;
    h$ ^= (int) ((duration >>> 32) ^ duration);
    h$ *= 1000003;
    h$ ^= (l_ep == null) ? 0 : l_ep.hashCode();
    h$ *= 1000003;
    h$ ^= (r_ep == null) ? 0 : r_ep.hashCode();
    h$ *= 1000003;
    h$ ^= annotations.hashCode();
    h$ *= 1000003;
    h$ ^= tags.hashCode();
    h$ *= 1000003;
    h$ ^= (annotation_query == null) ? 0 : annotation_query.hashCode();
    h$ *= 1000003;
    h$ ^= debug ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= shared ? 1231 : 1237;
    return h$;
  }

}
