/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.ebi.interpro.scan.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Location(s) of match on protein sequence
 *
 * @author  Antony Quinn
 * @version $Id$
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@XmlType(name="HmmerLocationType", propOrder={"score", "evalue", "hmmStart", "hmmEnd", "hmmBounds"})
abstract class HmmerLocation extends Location {

    @Column(nullable = false)
    private int hmmStart;

    @Column (nullable = false)
    private int hmmEnd;

    @Column (nullable = false)
    private HmmBounds hmmBounds;

    @Column (nullable = false)
    private double evalue;

    @Column (nullable = false)
    private double score;

    /**
     * protected no-arg constructor required by JPA - DO NOT USE DIRECTLY.
     */
    protected HmmerLocation() {}

    // Don't use Builder pattern because all fields are required
    public HmmerLocation(int start, int end, double score, double evalue,
                         int hmmStart, int hmmEnd, HmmBounds hmmBounds) {
        super(start, end);
        setHmmStart(hmmStart);
        setHmmEnd(hmmEnd);
        setHmmBounds(hmmBounds);
        setEvalue(evalue);
        setScore(score);
    }

    @XmlAttribute(name="hmm-start", required=true)
    public int getHmmStart() {
        return hmmStart;
    }

    private void setHmmStart(int hmmStart) {
        this.hmmStart = hmmStart;
    }

    @XmlAttribute(name="hmm-end", required=true)
    public int getHmmEnd() {
        return hmmEnd;
    }

    private void setHmmEnd(int hmmEnd) {
        this.hmmEnd = hmmEnd;
    }

    @XmlAttribute(name="hmm-bounds", required=true)
    public HmmBounds getHmmBounds() {
        return hmmBounds;
    }

    private void setHmmBounds(HmmBounds hmmBounds) {
        this.hmmBounds = hmmBounds;
    }

    @XmlAttribute(required=true)
    public double getEvalue() {
        return evalue;
    }

    private void setEvalue(double evalue) {
        this.evalue = evalue;
    }

    @XmlAttribute(required=true)
    public double getScore() {
        return score;
    }

    private void setScore(double score) {
        this.score = score;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HmmerLocation))
            return false;
        final HmmerLocation h = (HmmerLocation) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(hmmStart, h.hmmStart)
                .append(hmmEnd, h.hmmEnd)
                .append(hmmBounds, h.hmmBounds)
                .append(score, h.score)
                .append(evalue, h.evalue)
                .isEquals();
    }

    @Override public int hashCode() {
        return new HashCodeBuilder(19, 53)
                .appendSuper(super.hashCode())
                .append(hmmStart)
                .append(hmmEnd)
                .append(hmmBounds)
                .append(score)
                .append(evalue)
                .toHashCode();
    }

    @Override public String toString()  {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
