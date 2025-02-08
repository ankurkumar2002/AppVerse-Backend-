package zipkin2.elasticsearch;

import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import zipkin2.elasticsearch.internal.IndexNameFormatter;
import zipkin2.elasticsearch.internal.client.HttpCall;

@Generated("com.google.auto.value.extension.memoized.processor.MemoizeExtension")
final class AutoValue_ElasticsearchStorage extends $AutoValue_ElasticsearchStorage {
  private transient volatile BaseVersion version;

  private transient volatile HttpCall.Factory http;

  AutoValue_ElasticsearchStorage(ElasticsearchStorage.LazyHttpClient lazyHttpClient,
      String pipeline, boolean flushOnWrites, boolean strictTraceId, boolean searchEnabled,
      List<String> autocompleteKeys, int autocompleteTtl, int autocompleteCardinality,
      int indexShards, int indexReplicas, IndexNameFormatter indexNameFormatter,
      boolean ensureTemplates, int namesLookback, Integer templatePriority) {
    super(lazyHttpClient, pipeline, flushOnWrites, strictTraceId, searchEnabled, autocompleteKeys, autocompleteTtl, autocompleteCardinality, indexShards, indexReplicas, indexNameFormatter, ensureTemplates, namesLookback, templatePriority);
  }

  @Override
  public BaseVersion version() {
    if (version == null) {
      synchronized (this) {
        if (version == null) {
          version = super.version();
          if (version == null) {
            throw new NullPointerException("version() cannot return null");
          }
        }
      }
    }
    return version;
  }

  @Override
  HttpCall.Factory http() {
    if (http == null) {
      synchronized (this) {
        if (http == null) {
          http = super.http();
          if (http == null) {
            throw new NullPointerException("http() cannot return null");
          }
        }
      }
    }
    return http;
  }
}
