package zipkin2.elasticsearch;

import java.util.List;
import javax.annotation.processing.Generated;
import zipkin2.elasticsearch.internal.IndexNameFormatter;
import zipkin2.internal.Nullable;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
abstract class $AutoValue_ElasticsearchStorage extends ElasticsearchStorage {

  private final ElasticsearchStorage.LazyHttpClient lazyHttpClient;

  @Nullable
  private final String pipeline;

  private final boolean flushOnWrites;

  private final boolean strictTraceId;

  private final boolean searchEnabled;

  private final List<String> autocompleteKeys;

  private final int autocompleteTtl;

  private final int autocompleteCardinality;

  private final int indexShards;

  private final int indexReplicas;

  private final IndexNameFormatter indexNameFormatter;

  private final boolean ensureTemplates;

  private final int namesLookback;

  @Nullable
  private final Integer templatePriority;

  $AutoValue_ElasticsearchStorage(
      ElasticsearchStorage.LazyHttpClient lazyHttpClient,
      @Nullable String pipeline,
      boolean flushOnWrites,
      boolean strictTraceId,
      boolean searchEnabled,
      List<String> autocompleteKeys,
      int autocompleteTtl,
      int autocompleteCardinality,
      int indexShards,
      int indexReplicas,
      IndexNameFormatter indexNameFormatter,
      boolean ensureTemplates,
      int namesLookback,
      @Nullable Integer templatePriority) {
    if (lazyHttpClient == null) {
      throw new NullPointerException("Null lazyHttpClient");
    }
    this.lazyHttpClient = lazyHttpClient;
    this.pipeline = pipeline;
    this.flushOnWrites = flushOnWrites;
    this.strictTraceId = strictTraceId;
    this.searchEnabled = searchEnabled;
    if (autocompleteKeys == null) {
      throw new NullPointerException("Null autocompleteKeys");
    }
    this.autocompleteKeys = autocompleteKeys;
    this.autocompleteTtl = autocompleteTtl;
    this.autocompleteCardinality = autocompleteCardinality;
    this.indexShards = indexShards;
    this.indexReplicas = indexReplicas;
    if (indexNameFormatter == null) {
      throw new NullPointerException("Null indexNameFormatter");
    }
    this.indexNameFormatter = indexNameFormatter;
    this.ensureTemplates = ensureTemplates;
    this.namesLookback = namesLookback;
    this.templatePriority = templatePriority;
  }

  @Override
  ElasticsearchStorage.LazyHttpClient lazyHttpClient() {
    return lazyHttpClient;
  }

  @Nullable
  @Override
  public String pipeline() {
    return pipeline;
  }

  @Override
  public boolean flushOnWrites() {
    return flushOnWrites;
  }

  @Override
  public boolean strictTraceId() {
    return strictTraceId;
  }

  @Override
  boolean searchEnabled() {
    return searchEnabled;
  }

  @Override
  List<String> autocompleteKeys() {
    return autocompleteKeys;
  }

  @Override
  int autocompleteTtl() {
    return autocompleteTtl;
  }

  @Override
  int autocompleteCardinality() {
    return autocompleteCardinality;
  }

  @Override
  int indexShards() {
    return indexShards;
  }

  @Override
  int indexReplicas() {
    return indexReplicas;
  }

  @Override
  public IndexNameFormatter indexNameFormatter() {
    return indexNameFormatter;
  }

  @Override
  boolean ensureTemplates() {
    return ensureTemplates;
  }

  @Override
  public int namesLookback() {
    return namesLookback;
  }

  @Nullable
  @Override
  Integer templatePriority() {
    return templatePriority;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ElasticsearchStorage) {
      ElasticsearchStorage that = (ElasticsearchStorage) o;
      return this.lazyHttpClient.equals(that.lazyHttpClient())
          && (this.pipeline == null ? that.pipeline() == null : this.pipeline.equals(that.pipeline()))
          && this.flushOnWrites == that.flushOnWrites()
          && this.strictTraceId == that.strictTraceId()
          && this.searchEnabled == that.searchEnabled()
          && this.autocompleteKeys.equals(that.autocompleteKeys())
          && this.autocompleteTtl == that.autocompleteTtl()
          && this.autocompleteCardinality == that.autocompleteCardinality()
          && this.indexShards == that.indexShards()
          && this.indexReplicas == that.indexReplicas()
          && this.indexNameFormatter.equals(that.indexNameFormatter())
          && this.ensureTemplates == that.ensureTemplates()
          && this.namesLookback == that.namesLookback()
          && (this.templatePriority == null ? that.templatePriority() == null : this.templatePriority.equals(that.templatePriority()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= lazyHttpClient.hashCode();
    h$ *= 1000003;
    h$ ^= (pipeline == null) ? 0 : pipeline.hashCode();
    h$ *= 1000003;
    h$ ^= flushOnWrites ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= strictTraceId ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= searchEnabled ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= autocompleteKeys.hashCode();
    h$ *= 1000003;
    h$ ^= autocompleteTtl;
    h$ *= 1000003;
    h$ ^= autocompleteCardinality;
    h$ *= 1000003;
    h$ ^= indexShards;
    h$ *= 1000003;
    h$ ^= indexReplicas;
    h$ *= 1000003;
    h$ ^= indexNameFormatter.hashCode();
    h$ *= 1000003;
    h$ ^= ensureTemplates ? 1231 : 1237;
    h$ *= 1000003;
    h$ ^= namesLookback;
    h$ *= 1000003;
    h$ ^= (templatePriority == null) ? 0 : templatePriority.hashCode();
    return h$;
  }

  @Override
  ElasticsearchStorage.Builder toBuilder() {
    return new AutoValue_ElasticsearchStorage.Builder(this);
  }

  static class Builder extends ElasticsearchStorage.Builder {
    private ElasticsearchStorage.LazyHttpClient lazyHttpClient;
    private String pipeline;
    private boolean flushOnWrites;
    private boolean strictTraceId;
    private boolean searchEnabled;
    private List<String> autocompleteKeys;
    private int autocompleteTtl;
    private int autocompleteCardinality;
    private int indexShards;
    private int indexReplicas;
    private IndexNameFormatter.Builder indexNameFormatterBuilder$;
    private IndexNameFormatter indexNameFormatter;
    private boolean ensureTemplates;
    private int namesLookback;
    private Integer templatePriority;
    private short set$0;
    Builder() {
    }
    Builder(ElasticsearchStorage source) {
      this.lazyHttpClient = source.lazyHttpClient();
      this.pipeline = source.pipeline();
      this.flushOnWrites = source.flushOnWrites();
      this.strictTraceId = source.strictTraceId();
      this.searchEnabled = source.searchEnabled();
      this.autocompleteKeys = source.autocompleteKeys();
      this.autocompleteTtl = source.autocompleteTtl();
      this.autocompleteCardinality = source.autocompleteCardinality();
      this.indexShards = source.indexShards();
      this.indexReplicas = source.indexReplicas();
      this.indexNameFormatter = source.indexNameFormatter();
      this.ensureTemplates = source.ensureTemplates();
      this.namesLookback = source.namesLookback();
      this.templatePriority = source.templatePriority();
      set$0 = (short) 0x1ff;
    }
    @Override
    ElasticsearchStorage.Builder lazyHttpClient(ElasticsearchStorage.LazyHttpClient lazyHttpClient) {
      if (lazyHttpClient == null) {
        throw new NullPointerException("Null lazyHttpClient");
      }
      this.lazyHttpClient = lazyHttpClient;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder pipeline(String pipeline) {
      this.pipeline = pipeline;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder flushOnWrites(boolean flushOnWrites) {
      this.flushOnWrites = flushOnWrites;
      set$0 |= (short) 1;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder strictTraceId(boolean strictTraceId) {
      this.strictTraceId = strictTraceId;
      set$0 |= (short) 2;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder searchEnabled(boolean searchEnabled) {
      this.searchEnabled = searchEnabled;
      set$0 |= (short) 4;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder autocompleteKeys(List<String> autocompleteKeys) {
      if (autocompleteKeys == null) {
        throw new NullPointerException("Null autocompleteKeys");
      }
      this.autocompleteKeys = autocompleteKeys;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder autocompleteTtl(int autocompleteTtl) {
      this.autocompleteTtl = autocompleteTtl;
      set$0 |= (short) 8;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder autocompleteCardinality(int autocompleteCardinality) {
      this.autocompleteCardinality = autocompleteCardinality;
      set$0 |= (short) 0x10;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder indexShards(int indexShards) {
      this.indexShards = indexShards;
      set$0 |= (short) 0x20;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder indexReplicas(int indexReplicas) {
      this.indexReplicas = indexReplicas;
      set$0 |= (short) 0x40;
      return this;
    }
    @Override
    IndexNameFormatter.Builder indexNameFormatterBuilder() {
      if (indexNameFormatterBuilder$ == null) {
        if (indexNameFormatter == null) {
          indexNameFormatterBuilder$ = IndexNameFormatter.newBuilder();
        } else {
          indexNameFormatterBuilder$ = indexNameFormatter.toBuilder();
          indexNameFormatter = null;
        }
      }
      return indexNameFormatterBuilder$;
    }
    @Override
    public ElasticsearchStorage.Builder ensureTemplates(boolean ensureTemplates) {
      this.ensureTemplates = ensureTemplates;
      set$0 |= (short) 0x80;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder namesLookback(int namesLookback) {
      this.namesLookback = namesLookback;
      set$0 |= (short) 0x100;
      return this;
    }
    @Override
    public ElasticsearchStorage.Builder templatePriority(@Nullable Integer templatePriority) {
      this.templatePriority = templatePriority;
      return this;
    }
    @Override
    public ElasticsearchStorage build() {
      if (indexNameFormatterBuilder$ != null) {
        this.indexNameFormatter = indexNameFormatterBuilder$.build();
      } else if (this.indexNameFormatter == null) {
        IndexNameFormatter.Builder indexNameFormatter$builder = IndexNameFormatter.newBuilder();
        this.indexNameFormatter = indexNameFormatter$builder.build();
      }
      if (set$0 != 0x1ff
          || this.lazyHttpClient == null
          || this.autocompleteKeys == null) {
        StringBuilder missing = new StringBuilder();
        if (this.lazyHttpClient == null) {
          missing.append(" lazyHttpClient");
        }
        if ((set$0 & 1) == 0) {
          missing.append(" flushOnWrites");
        }
        if ((set$0 & 2) == 0) {
          missing.append(" strictTraceId");
        }
        if ((set$0 & 4) == 0) {
          missing.append(" searchEnabled");
        }
        if (this.autocompleteKeys == null) {
          missing.append(" autocompleteKeys");
        }
        if ((set$0 & 8) == 0) {
          missing.append(" autocompleteTtl");
        }
        if ((set$0 & 0x10) == 0) {
          missing.append(" autocompleteCardinality");
        }
        if ((set$0 & 0x20) == 0) {
          missing.append(" indexShards");
        }
        if ((set$0 & 0x40) == 0) {
          missing.append(" indexReplicas");
        }
        if ((set$0 & 0x80) == 0) {
          missing.append(" ensureTemplates");
        }
        if ((set$0 & 0x100) == 0) {
          missing.append(" namesLookback");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_ElasticsearchStorage(
          this.lazyHttpClient,
          this.pipeline,
          this.flushOnWrites,
          this.strictTraceId,
          this.searchEnabled,
          this.autocompleteKeys,
          this.autocompleteTtl,
          this.autocompleteCardinality,
          this.indexShards,
          this.indexReplicas,
          this.indexNameFormatter,
          this.ensureTemplates,
          this.namesLookback,
          this.templatePriority);
    }
  }

}
