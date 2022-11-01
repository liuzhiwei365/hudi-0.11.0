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
public class HoodieClusteringPlan extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 9068860476381409048L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"HoodieClusteringPlan\",\"namespace\":\"org.apache.hudi.avro.model\",\"fields\":[{\"name\":\"inputGroups\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieClusteringGroup\",\"fields\":[{\"name\":\"slices\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"HoodieSliceInfo\",\"fields\":[{\"name\":\"dataFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"deltaFilePaths\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}],\"default\":null},{\"name\":\"fileId\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"partitionPath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"bootstrapFilePath\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"metrics\",\"type\":[\"null\",{\"type\":\"map\",\"values\":\"double\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"numOutputFileGroups\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}}],\"default\":null},{\"name\":\"strategy\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"HoodieClusteringStrategy\",\"fields\":[{\"name\":\"strategyClassName\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"strategyParams\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1}]}],\"default\":null},{\"name\":\"extraMetadata\",\"type\":[\"null\",{\"type\":\"map\",\"values\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"version\",\"type\":[\"int\",\"null\"],\"default\":1},{\"name\":\"preserveHoodieMetadata\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<HoodieClusteringPlan> ENCODER =
      new BinaryMessageEncoder<HoodieClusteringPlan>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<HoodieClusteringPlan> DECODER =
      new BinaryMessageDecoder<HoodieClusteringPlan>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<HoodieClusteringPlan> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<HoodieClusteringPlan> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<HoodieClusteringPlan>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this HoodieClusteringPlan to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a HoodieClusteringPlan from a ByteBuffer. */
  public static HoodieClusteringPlan fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> inputGroups;
  @Deprecated public org.apache.hudi.avro.model.HoodieClusteringStrategy strategy;
  @Deprecated public java.util.Map<java.lang.String,java.lang.String> extraMetadata;
  @Deprecated public java.lang.Integer version;
  @Deprecated public java.lang.Boolean preserveHoodieMetadata;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public HoodieClusteringPlan() {}

  /**
   * All-args constructor.
   * @param inputGroups The new value for inputGroups
   * @param strategy The new value for strategy
   * @param extraMetadata The new value for extraMetadata
   * @param version The new value for version
   * @param preserveHoodieMetadata The new value for preserveHoodieMetadata
   */
  public HoodieClusteringPlan(java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> inputGroups, org.apache.hudi.avro.model.HoodieClusteringStrategy strategy, java.util.Map<java.lang.String,java.lang.String> extraMetadata, java.lang.Integer version, java.lang.Boolean preserveHoodieMetadata) {
    this.inputGroups = inputGroups;
    this.strategy = strategy;
    this.extraMetadata = extraMetadata;
    this.version = version;
    this.preserveHoodieMetadata = preserveHoodieMetadata;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return inputGroups;
    case 1: return strategy;
    case 2: return extraMetadata;
    case 3: return version;
    case 4: return preserveHoodieMetadata;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: inputGroups = (java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup>)value$; break;
    case 1: strategy = (org.apache.hudi.avro.model.HoodieClusteringStrategy)value$; break;
    case 2: extraMetadata = (java.util.Map<java.lang.String,java.lang.String>)value$; break;
    case 3: version = (java.lang.Integer)value$; break;
    case 4: preserveHoodieMetadata = (java.lang.Boolean)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'inputGroups' field.
   * @return The value of the 'inputGroups' field.
   */
  public java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> getInputGroups() {
    return inputGroups;
  }

  /**
   * Sets the value of the 'inputGroups' field.
   * @param value the value to set.
   */
  public void setInputGroups(java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> value) {
    this.inputGroups = value;
  }

  /**
   * Gets the value of the 'strategy' field.
   * @return The value of the 'strategy' field.
   */
  public org.apache.hudi.avro.model.HoodieClusteringStrategy getStrategy() {
    return strategy;
  }

  /**
   * Sets the value of the 'strategy' field.
   * @param value the value to set.
   */
  public void setStrategy(org.apache.hudi.avro.model.HoodieClusteringStrategy value) {
    this.strategy = value;
  }

  /**
   * Gets the value of the 'extraMetadata' field.
   * @return The value of the 'extraMetadata' field.
   */
  public java.util.Map<java.lang.String,java.lang.String> getExtraMetadata() {
    return extraMetadata;
  }

  /**
   * Sets the value of the 'extraMetadata' field.
   * @param value the value to set.
   */
  public void setExtraMetadata(java.util.Map<java.lang.String,java.lang.String> value) {
    this.extraMetadata = value;
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.Integer getVersion() {
    return version;
  }

  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.Integer value) {
    this.version = value;
  }

  /**
   * Gets the value of the 'preserveHoodieMetadata' field.
   * @return The value of the 'preserveHoodieMetadata' field.
   */
  public java.lang.Boolean getPreserveHoodieMetadata() {
    return preserveHoodieMetadata;
  }

  /**
   * Sets the value of the 'preserveHoodieMetadata' field.
   * @param value the value to set.
   */
  public void setPreserveHoodieMetadata(java.lang.Boolean value) {
    this.preserveHoodieMetadata = value;
  }

  /**
   * Creates a new HoodieClusteringPlan RecordBuilder.
   * @return A new HoodieClusteringPlan RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieClusteringPlan.Builder newBuilder() {
    return new org.apache.hudi.avro.model.HoodieClusteringPlan.Builder();
  }

  /**
   * Creates a new HoodieClusteringPlan RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new HoodieClusteringPlan RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieClusteringPlan.Builder newBuilder(org.apache.hudi.avro.model.HoodieClusteringPlan.Builder other) {
    return new org.apache.hudi.avro.model.HoodieClusteringPlan.Builder(other);
  }

  /**
   * Creates a new HoodieClusteringPlan RecordBuilder by copying an existing HoodieClusteringPlan instance.
   * @param other The existing instance to copy.
   * @return A new HoodieClusteringPlan RecordBuilder
   */
  public static org.apache.hudi.avro.model.HoodieClusteringPlan.Builder newBuilder(org.apache.hudi.avro.model.HoodieClusteringPlan other) {
    return new org.apache.hudi.avro.model.HoodieClusteringPlan.Builder(other);
  }

  /**
   * RecordBuilder for HoodieClusteringPlan instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<HoodieClusteringPlan>
    implements org.apache.avro.data.RecordBuilder<HoodieClusteringPlan> {

    private java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> inputGroups;
    private org.apache.hudi.avro.model.HoodieClusteringStrategy strategy;
    private org.apache.hudi.avro.model.HoodieClusteringStrategy.Builder strategyBuilder;
    private java.util.Map<java.lang.String,java.lang.String> extraMetadata;
    private java.lang.Integer version;
    private java.lang.Boolean preserveHoodieMetadata;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieClusteringPlan.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.inputGroups)) {
        this.inputGroups = data().deepCopy(fields()[0].schema(), other.inputGroups);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.strategy)) {
        this.strategy = data().deepCopy(fields()[1].schema(), other.strategy);
        fieldSetFlags()[1] = true;
      }
      if (other.hasStrategyBuilder()) {
        this.strategyBuilder = org.apache.hudi.avro.model.HoodieClusteringStrategy.newBuilder(other.getStrategyBuilder());
      }
      if (isValidValue(fields()[2], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[2].schema(), other.extraMetadata);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.preserveHoodieMetadata)) {
        this.preserveHoodieMetadata = data().deepCopy(fields()[4].schema(), other.preserveHoodieMetadata);
        fieldSetFlags()[4] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing HoodieClusteringPlan instance
     * @param other The existing instance to copy.
     */
    private Builder(org.apache.hudi.avro.model.HoodieClusteringPlan other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.inputGroups)) {
        this.inputGroups = data().deepCopy(fields()[0].schema(), other.inputGroups);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.strategy)) {
        this.strategy = data().deepCopy(fields()[1].schema(), other.strategy);
        fieldSetFlags()[1] = true;
      }
      this.strategyBuilder = null;
      if (isValidValue(fields()[2], other.extraMetadata)) {
        this.extraMetadata = data().deepCopy(fields()[2].schema(), other.extraMetadata);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.preserveHoodieMetadata)) {
        this.preserveHoodieMetadata = data().deepCopy(fields()[4].schema(), other.preserveHoodieMetadata);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'inputGroups' field.
      * @return The value.
      */
    public java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> getInputGroups() {
      return inputGroups;
    }

    /**
      * Sets the value of the 'inputGroups' field.
      * @param value The value of 'inputGroups'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setInputGroups(java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup> value) {
      validate(fields()[0], value);
      this.inputGroups = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'inputGroups' field has been set.
      * @return True if the 'inputGroups' field has been set, false otherwise.
      */
    public boolean hasInputGroups() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'inputGroups' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clearInputGroups() {
      inputGroups = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'strategy' field.
      * @return The value.
      */
    public org.apache.hudi.avro.model.HoodieClusteringStrategy getStrategy() {
      return strategy;
    }

    /**
      * Sets the value of the 'strategy' field.
      * @param value The value of 'strategy'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setStrategy(org.apache.hudi.avro.model.HoodieClusteringStrategy value) {
      validate(fields()[1], value);
      this.strategyBuilder = null;
      this.strategy = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'strategy' field has been set.
      * @return True if the 'strategy' field has been set, false otherwise.
      */
    public boolean hasStrategy() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'strategy' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieClusteringStrategy.Builder getStrategyBuilder() {
      if (strategyBuilder == null) {
        if (hasStrategy()) {
          setStrategyBuilder(org.apache.hudi.avro.model.HoodieClusteringStrategy.newBuilder(strategy));
        } else {
          setStrategyBuilder(org.apache.hudi.avro.model.HoodieClusteringStrategy.newBuilder());
        }
      }
      return strategyBuilder;
    }

    /**
     * Sets the Builder instance for the 'strategy' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setStrategyBuilder(org.apache.hudi.avro.model.HoodieClusteringStrategy.Builder value) {
      clearStrategy();
      strategyBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'strategy' field has an active Builder instance
     * @return True if the 'strategy' field has an active Builder instance
     */
    public boolean hasStrategyBuilder() {
      return strategyBuilder != null;
    }

    /**
      * Clears the value of the 'strategy' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clearStrategy() {
      strategy = null;
      strategyBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'extraMetadata' field.
      * @return The value.
      */
    public java.util.Map<java.lang.String,java.lang.String> getExtraMetadata() {
      return extraMetadata;
    }

    /**
      * Sets the value of the 'extraMetadata' field.
      * @param value The value of 'extraMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setExtraMetadata(java.util.Map<java.lang.String,java.lang.String> value) {
      validate(fields()[2], value);
      this.extraMetadata = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'extraMetadata' field has been set.
      * @return True if the 'extraMetadata' field has been set, false otherwise.
      */
    public boolean hasExtraMetadata() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'extraMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clearExtraMetadata() {
      extraMetadata = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.Integer getVersion() {
      return version;
    }

    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setVersion(java.lang.Integer value) {
      validate(fields()[3], value);
      this.version = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clearVersion() {
      version = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'preserveHoodieMetadata' field.
      * @return The value.
      */
    public java.lang.Boolean getPreserveHoodieMetadata() {
      return preserveHoodieMetadata;
    }

    /**
      * Sets the value of the 'preserveHoodieMetadata' field.
      * @param value The value of 'preserveHoodieMetadata'.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder setPreserveHoodieMetadata(java.lang.Boolean value) {
      validate(fields()[4], value);
      this.preserveHoodieMetadata = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'preserveHoodieMetadata' field has been set.
      * @return True if the 'preserveHoodieMetadata' field has been set, false otherwise.
      */
    public boolean hasPreserveHoodieMetadata() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'preserveHoodieMetadata' field.
      * @return This builder.
      */
    public org.apache.hudi.avro.model.HoodieClusteringPlan.Builder clearPreserveHoodieMetadata() {
      preserveHoodieMetadata = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public HoodieClusteringPlan build() {
      try {
        HoodieClusteringPlan record = new HoodieClusteringPlan();
        record.inputGroups = fieldSetFlags()[0] ? this.inputGroups : (java.util.List<org.apache.hudi.avro.model.HoodieClusteringGroup>) defaultValue(fields()[0]);
        if (strategyBuilder != null) {
          record.strategy = this.strategyBuilder.build();
        } else {
          record.strategy = fieldSetFlags()[1] ? this.strategy : (org.apache.hudi.avro.model.HoodieClusteringStrategy) defaultValue(fields()[1]);
        }
        record.extraMetadata = fieldSetFlags()[2] ? this.extraMetadata : (java.util.Map<java.lang.String,java.lang.String>) defaultValue(fields()[2]);
        record.version = fieldSetFlags()[3] ? this.version : (java.lang.Integer) defaultValue(fields()[3]);
        record.preserveHoodieMetadata = fieldSetFlags()[4] ? this.preserveHoodieMetadata : (java.lang.Boolean) defaultValue(fields()[4]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<HoodieClusteringPlan>
    WRITER$ = (org.apache.avro.io.DatumWriter<HoodieClusteringPlan>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<HoodieClusteringPlan>
    READER$ = (org.apache.avro.io.DatumReader<HoodieClusteringPlan>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
