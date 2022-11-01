/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.apache.hudi.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class HoodieCompactionOperation extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1376499005954309214L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieCompactionOperation\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"baseInstantTime\",\"type\":[\"null\",\"string\"]},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":\"string\"}],\"default\":null},{\"name\":\"dataFilePath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",\"string\"]},{\"name\":\"partitionPath\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieCompactionOperation> ENCODER =
      new BinaryMessageEncoder<HoodieCompactionOperation>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieCompactionOperation> DECODER =
      new BinaryMessageDecoder<HoodieCompactionOperation>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieCompactionOperation> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieCompactionOperation> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieCompactionOperation>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieCompactionOperation to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieCompactionOperation from a ByteBuffer. */
  public static HoodieCompactionOperation fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence baseInstantTime;
  @Deprecated public java.util.List<java.lang.CharSequence> deltaFilePaths;
  @Deprecated public java.lang.CharSequence dataFilePath;
  @Deprecated public java.lang.CharSequence fileId;
  @Deprecated public java.lang.CharSequence partitionPath;
  @Deprecated public java.util.Map<java.lang.CharSequence,java.lang.Double> metrics;
  @Deprecated public java.lang.CharSequence bootstrapFilePath;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieCompactionOperation() {}

  /**
   * All-args constructor.
   * @param baseInstantTime The new value for baseInstantTime
   * @param deltaFilePaths The new value for deltaFilePaths
   * @param dataFilePath The new value for dataFilePath
   * @param fileId The new value for fileId
   * @param partitionPath The new value for partitionPath
   * @param metrics The new value for metrics
   * @param bootstrapFilePath The new value for bootstrapFilePath
   */
  public HoodieCompactionOperation(java.lang.CharSequence baseInstantTime, java.util.List<java.lang.CharSequence> deltaFilePaths, java.lang.CharSequence dataFilePath, java.lang.CharSequence fileId, java.lang.CharSequence partitionPath, java.util.Map<java.lang.CharSequence,java.lang.Double> metrics, java.lang.CharSequence bootstrapFilePath) {
    this.baseInstantTime = baseInstantTime;
    this.deltaFilePaths = deltaFilePaths;
    this.dataFilePath = dataFilePath;
    this.fileId = fileId;
    this.partitionPath = partitionPath;
    this.metrics = metrics;
    this.bootstrapFilePath = bootstrapFilePath;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return baseInstantTime;
    case 1: return deltaFilePaths;
    case 2: return dataFilePath;
    case 3: return fileId;
    case 4: return partitionPath;
    case 5: return metrics;
    case 6: return bootstrapFilePath;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: baseInstantTime = (java.lang.CharSequence)value$; break;
    case 1: deltaFilePaths = (java.util.List<java.lang.CharSequence>)value$; break;
    case 2: dataFilePath = (java.lang.CharSequence)value$; break;
    case 3: fileId = (java.lang.CharSequence)value$; break;
    case 4: partitionPath = (java.lang.CharSequence)value$; break;
    case 5: metrics = (java.util.Map<java.lang.CharSequence,java.lang.Double>)value$; break;
    case 6: bootstrapFilePath = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'baseInstantTime' field.
   * @return The value of the 'baseInstantTime' field.
   */
  public java.lang.CharSequence getBaseInstantTime() {
    return baseInstantTime;
  }

  /**
   * Sets the value of the 'baseInstantTime' field.
   * @param value the value to set.
   */
  public void setBaseInstantTime(java.lang.CharSequence value) {
    this.baseInstantTime = value;
  }

  /**
   * Gets the value of the 'deltaFilePaths' field.
   * @return The value of the 'deltaFilePaths' field.
   */
  public java.util.List<java.lang.CharSequence> getDeltaFilePaths() {
    return deltaFilePaths;
  }

  /**
   * Sets the value of the 'deltaFilePaths' field.
   * @param value the value to set.
   */
  public void setDeltaFilePaths(java.util.List<java.lang.CharSequence> value) {
    this.deltaFilePaths = value;
  }

  /**
   * Gets the value of the 'dataFilePath' field.
   * @return The value of the 'dataFilePath' field.
   */
  public java.lang.CharSequence getDataFilePath() {
    return dataFilePath;
  }

  /**
   * Sets the value of the 'dataFilePath' field.
   * @param value the value to set.
   */
  public void setDataFilePath(java.lang.CharSequence value) {
    this.dataFilePath = value;
  }

  /**
   * Gets the value of the 'fileId' field.
   * @return The value of the 'fileId' field.
   */
  public java.lang.CharSequence getFileId() {
    return fileId;
  }

  /**
   * Sets the value of the 'fileId' field.
   * @param value the value to set.
   */
  public void setFileId(java.lang.CharSequence value) {
    this.fileId = value;
  }

  /**
   * Gets the value of the 'partitionPath' field.
   * @return The value of the 'partitionPath' field.
   */
  public java.lang.CharSequence getPartitionPath() {
    return partitionPath;
  }

  /**
   * Sets the value of the 'partitionPath' field.
   * @param value the value to set.
   */
  public void setPartitionPath(java.lang.CharSequence value) {
    this.partitionPath = value;
  }

  /**
   * Gets the value of the 'metrics' field.
   * @return The value of the 'metrics' field.
   */
  public java.util.Map<java.lang.CharSequence,java.lang.Double> getMetrics() {
    return metrics;
  }

  /**
   * Sets the value of the 'metrics' field.
   * @param value the value to set.
   */
  public void setMetrics(java.util.Map<java.lang.CharSequence,java.lang.Double> value) {
    this.metrics = value;
  }

  /**
   * Gets the value of the 'bootstrapFilePath' field.
   * @return The value of the 'bootstrapFilePath' field.
   */
  public java.lang.CharSequence getBootstrapFilePath() {
    return bootstrapFilePath;
  }

  /**
   * Sets the value of the 'bootstrapFilePath' field.
   * @param value the value to set.
   */
  public void setBootstrapFilePath(java.lang.CharSequence value) {
    this.bootstrapFilePath = value;
  }

  /**
   * Creates a new HoodieCompactionOperation RecordBuilder.
   * @return A new HoodieCompactionOperation RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionOperation.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieCompactionOperation.Builder();
  }

  /**
   * Creates a new HoodieCompactionOperation RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieCompactionOperation RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionOperation.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionOperation.Builder other) {
    return new org.apache.hudi.avro.model.HoodieCompactionOperation.Builder(other);
  }

  /**
   * Creates a new HoodieCompactionOperation RecordBuilder by copying an existing HoodieCompactionOperation instance.
   * @param other The existing instance to copy.
   * @return A new HoodieCompactionOperation RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieCompactionOperation.Builder newBuilder(org.apache.hudi.avro.model.HoodieCompactionOperation other) {
    return new org.apache.hudi.avro.model.HoodieCompactionOperation.Builder(other);
  }

  /**
   * RecordBuilder for HoodieCompactionOperation instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieCompactionOperation>
    implements org.apache.avro.data.RecordBuilder<HoodieCompactionOperation> {

    private java.lang.CharSequence baseInstantTime;
    private java.util.List<java.lang.CharSequence> deltaFilePaths;
    private java.lang.CharSequence dataFilePath;
    private java.lang.CharSequence fileId;
    private java.lang.CharSequence partitionPath;
    private java.util.Map<java.lang.CharSequence,java.lang.Double> metrics;
    private java.lang.CharSequence bootstrapFilePath;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionOperation.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.baseInstantTime)) {
        this.baseInstantTime = data().deepCopy(fields()[0].schema(), other.baseInstantTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.deltaFilePaths)) {
        this.deltaFilePaths = data().deepCopy(fields()[1].schema(), other.deltaFilePaths);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.dataFilePath)) {
        this.dataFilePath = data().deepCopy(fields()[2].schema(), other.dataFilePath);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.fileId)) {
        this.fileId = data().deepCopy(fields()[3].schema(), other.fileId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[4].schema(), other.partitionPath);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.metrics)) {
        this.metrics = data().deepCopy(fields()[5].schema(), other.metrics);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.bootstrapFilePath)) {
        this.bootstrapFilePath = data().deepCopy(fields()[6].schema(), other.bootstrapFilePath);
        fieldSetFlags()[6] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieCompactionOperation instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieCompactionOperation other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.baseInstantTime)) {
        this.baseInstantTime = data().deepCopy(fields()[0].schema(), other.baseInstantTime);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.deltaFilePaths)) {
        this.deltaFilePaths = data().deepCopy(fields()[1].schema(), other.deltaFilePaths);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.dataFilePath)) {
        this.dataFilePath = data().deepCopy(fields()[2].schema(), other.dataFilePath);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.fileId)) {
        this.fileId = data().deepCopy(fields()[3].schema(), other.fileId);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.partitionPath)) {
        this.partitionPath = data().deepCopy(fields()[4].schema(), other.partitionPath);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.metrics)) {
        this.metrics = data().deepCopy(fields()[5].schema(), other.metrics);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.bootstrapFilePath)) {
        this.bootstrapFilePath = data().deepCopy(fields()[6].schema(), other.bootstrapFilePath);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'baseInstantTime' field.
      * @return The value.
      */
    public java.lang.CharSequence getBaseInstantTime() {
      return baseInstantTime;
    }

    /**
      * Sets the value of the 'baseInstantTime' field.
      * @param value The value of 'baseInstantTime'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setBaseInstantTime(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.baseInstantTime = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'baseInstantTime' field has been set.
      * @return True if the 'baseInstantTime' field has been set, false otherwise.
      */
    public boolean hasBaseInstantTime() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'baseInstantTime' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearBaseInstantTime() {
      baseInstantTime = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'deltaFilePaths' field.
      * @return The value.
      */
    public java.util.List<java.lang.CharSequence> getDeltaFilePaths() {
      return deltaFilePaths;
    }

    /**
      * Sets the value of the 'deltaFilePaths' field.
      * @param value The value of 'deltaFilePaths'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setDeltaFilePaths(java.util.List<java.lang.CharSequence> value) {
      validate(fields()[1], value);
      this.deltaFilePaths = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'deltaFilePaths' field has been set.
      * @return True if the 'deltaFilePaths' field has been set, false otherwise.
      */
    public boolean hasDeltaFilePaths() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'deltaFilePaths' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearDeltaFilePaths() {
      deltaFilePaths = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'dataFilePath' field.
      * @return The value.
      */
    public java.lang.CharSequence getDataFilePath() {
      return dataFilePath;
    }

    /**
      * Sets the value of the 'dataFilePath' field.
      * @param value The value of 'dataFilePath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setDataFilePath(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.dataFilePath = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'dataFilePath' field has been set.
      * @return True if the 'dataFilePath' field has been set, false otherwise.
      */
    public boolean hasDataFilePath() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'dataFilePath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearDataFilePath() {
      dataFilePath = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'fileId' field.
      * @return The value.
      */
    public java.lang.CharSequence getFileId() {
      return fileId;
    }

    /**
      * Sets the value of the 'fileId' field.
      * @param value The value of 'fileId'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setFileId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.fileId = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'fileId' field has been set.
      * @return True if the 'fileId' field has been set, false otherwise.
      */
    public boolean hasFileId() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'fileId' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearFileId() {
      fileId = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'partitionPath' field.
      * @return The value.
      */
    public java.lang.CharSequence getPartitionPath() {
      return partitionPath;
    }

    /**
      * Sets the value of the 'partitionPath' field.
      * @param value The value of 'partitionPath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setPartitionPath(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.partitionPath = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'partitionPath' field has been set.
      * @return True if the 'partitionPath' field has been set, false otherwise.
      */
    public boolean hasPartitionPath() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'partitionPath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearPartitionPath() {
      partitionPath = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'metrics' field.
      * @return The value.
      */
    public java.util.Map<java.lang.CharSequence,java.lang.Double> getMetrics() {
      return metrics;
    }

    /**
      * Sets the value of the 'metrics' field.
      * @param value The value of 'metrics'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setMetrics(java.util.Map<java.lang.CharSequence,java.lang.Double> value) {
      validate(fields()[5], value);
      this.metrics = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'metrics' field has been set.
      * @return True if the 'metrics' field has been set, false otherwise.
      */
    public boolean hasMetrics() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'metrics' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearMetrics() {
      metrics = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'bootstrapFilePath' field.
      * @return The value.
      */
    public java.lang.CharSequence getBootstrapFilePath() {
      return bootstrapFilePath;
    }

    /**
      * Sets the value of the 'bootstrapFilePath' field.
      * @param value The value of 'bootstrapFilePath'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder setBootstrapFilePath(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.bootstrapFilePath = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'bootstrapFilePath' field has been set.
      * @return True if the 'bootstrapFilePath' field has been set, false otherwise.
      */
    public boolean hasBootstrapFilePath() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'bootstrapFilePath' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieCompactionOperation.Builder clearBootstrapFilePath() {
      bootstrapFilePath = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieCompactionOperation build() {
      try {
        HoodieCompactionOperation record = new HoodieCompactionOperation();
        record.baseInstantTime = fieldSetFlags()[0] ? this.baseInstantTime : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.deltaFilePaths = fieldSetFlags()[1] ? this.deltaFilePaths : (java.util.List<java.lang.CharSequence>) defaultValue(fields()[1]);
        record.dataFilePath = fieldSetFlags()[2] ? this.dataFilePath : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.fileId = fieldSetFlags()[3] ? this.fileId : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.partitionPath = fieldSetFlags()[4] ? this.partitionPath : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.metrics = fieldSetFlags()[5] ? this.metrics : (java.util.Map<java.lang.CharSequence,java.lang.Double>) defaultValue(fields()[5]);
        record.bootstrapFilePath = fieldSetFlags()[6] ? this.bootstrapFilePath : (java.lang.CharSequence) defaultValue(fields()[6]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieCompactionOperation>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieCompactionOperation>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieCompactionOperation>
    READER$ = (org.apache.avro.io.DatumReader<HoodieCompactionOperation>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
