/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.threeten.api;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import static org.assertj.threeten.error.ShouldBeAfter.shouldBeAfter;
import static org.assertj.threeten.error.ShouldBeAfterOrEqualsTo.shouldBeAfterOrEqualsTo;
import static org.assertj.threeten.error.ShouldBeBefore.shouldBeBefore;
import static org.assertj.threeten.error.ShouldBeBeforeOrEqualsTo.shouldBeBeforeOrEqualsTo;
import static org.assertj.threeten.error.ShouldBeEqualIgnoringHours.shouldBeEqualIgnoringHours;
import static org.assertj.threeten.error.ShouldBeEqualIgnoringMinutes.shouldBeEqualIgnoringMinutes;
import static org.assertj.threeten.error.ShouldBeEqualIgnoringNanos.shouldBeEqualIgnoringNanos;
import static org.assertj.threeten.error.ShouldBeEqualIgnoringSeconds.shouldBeEqualIgnoringSeconds;

/**
 * Assertions for {@link ZonedDateTime} type from new Date &amp; Time API introduced in Java 8.
 *
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class ZonedDateTimeAssert extends AbstractAssert<ZonedDateTimeAssert, ZonedDateTime> {

  public static final String NULL_DATE_TIME_PARAMETER_MESSAGE = "The ZonedDateTime to compare actual with should not be null";

  /**
   * Creates a new <code>{@link ZonedDateTimeAssert}</code>.
   *
   * @param selfType the "self type"
   * @param actual the actual value to verify
   */
  protected ZonedDateTimeAssert(Class<ZonedDateTimeAssert> selfType, ZonedDateTime actual) {
    super(actual, selfType);
  }

  // visible for test
  protected ZonedDateTime getActual() {
    return actual;
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is <b>strictly</b> before the given one.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T23:59:59Z")).isBefore(ZonedDateTime.parse("2000-01-02T00:00:00Z"));
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly before the given one.
   */
  public ZonedDateTimeAssert isBefore(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!actual.isBefore(other)) {
      throw Failures.instance().failure(info, shouldBeBefore(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isBefore(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which
   * must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   *
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T23:59:59Z")).isBefore("2000-01-02T00:00:00Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly before the {@link ZonedDateTime} built
   *           from given String.
   */
  public ZonedDateTimeAssert isBefore(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isBefore(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is before or equals to the given one.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T23:59:59Z"))
   *                                       .isBeforeOrEqualTo(ZonedDateTime.parse("2000-01-01T23:59:59Z"))
   *                                       .isBeforeOrEqualTo(ZonedDateTime.parse("2000-01-02T00:00:00Z"));
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZoneDateTime} is not before or equals to the given one.
   */
  public ZonedDateTimeAssert isBeforeOrEqualTo(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (actual.isAfter(other)) {
      throw Failures.instance().failure(info, shouldBeBeforeOrEqualsTo(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isBeforeOrEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given
   * String, which must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check..
   * <p>
   * Example :
   *
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T23:59:59Z"))
   *                                       .isBeforeOrEqualTo("2000-01-01T23:59:59Z")
   *                                       .isBeforeOrEqualTo("2000-01-02T00:00:00Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not before or equals to the {@link ZonedDateTime}
   *           built from given String.
   */
  public ZonedDateTimeAssert isBeforeOrEqualTo(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isBeforeOrEqualTo(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is after or equals to the given one.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z"))
   *                                       .isAfterOrEqualTo(ZonedDateTime.parse("2000-01-01T00:00:00Z"))
   *                                       .isAfterOrEqualTo(ZonedDateTime.parse("1999-12-31T23:59:59Z"));
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not after or equals to the given one.
   */
  public ZonedDateTimeAssert isAfterOrEqualTo(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (actual.isBefore(other)) {
      throw Failures.instance().failure(info, shouldBeAfterOrEqualsTo(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isAfterOrEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given
   * String, which must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   *
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z"))
   *                                       .isAfterOrEqualTo("2000-01-01T00:00:00Z")
   *                                       .isAfterOrEqualTo("1999-12-31T23:59:59Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not after or equals to the {@link ZonedDateTime}
   *           built from given String.
   */
  public ZonedDateTimeAssert isAfterOrEqualTo(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isAfterOrEqualTo(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that the actual {@code ZonedDateTime} is <b>strictly</b> after the given one.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isAfter(ZonedDateTime.parse("1999-12-31T23:59:59Z"));
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly after the given one.
   */
  public ZonedDateTimeAssert isAfter(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!actual.isAfter(other)) {
      throw Failures.instance().failure(info, shouldBeAfter(actual, other));
    }
    return this;
  }

  /**
   * Same assertion as {@link #isAfter(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String, which
   * must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   *
   * <pre>
   * // use String in comparison to avoid conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isAfter("1999-12-31T23:59:59Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not strictly after the {@link ZonedDateTime} built
   *           from given String.
   */
  public ZonedDateTimeAssert isAfter(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isAfter(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month, day, hour, minute and second fields,
   * (nanosecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link org.threeten.bp.ZoneId} before comparison.
   * <p>
   * Assertion can fail with dateTimes in same chronological nanosecond time window, e.g :
   * <p>
   * 2000-01-01T00:00:<b>01.000000000</b> and 2000-01-01T00:00:<b>00.999999999</b>.
   * <p>
   * Assertion fails as second fields differ even if time difference is only 1ns.
   * <p>
   * Code example :
   *
   * <pre>
   * // successfull assertions
   * ZonedDateTime dateTime1 = ZonedDateTime.of(2000, 1, 1, 0, 0, 1, 0);
   * ZonedDateTime dateTime2 = ZonedDateTime.of(2000, 1, 1, 0, 0, 1, 456);
   * assertThat(dateTime1).isEqualToIgnoringNanos(dateTime2);
   *
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime dateTimeA = ZonedDateTime.of(2000, 1, 1, 0, 0, 1, 0);
   * ZonedDateTime dateTimeB = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 999999999);
   * assertThat(dateTimeA).isEqualToIgnoringNanos(dateTimeB);
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with nanoseconds ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringNanos(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringNanos(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringNanos(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@link ZonedDateTime} have same year, month, day, hour and minute fields (second and
   * nanosecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link org.threeten.bp.ZoneId} before comparison.
   * <p>
   * Assertion can fail with ZonedDateTimes in same chronological second time window, e.g :
   * <p>
   * 2000-01-01T00:<b>01:00</b>.000 and 2000-01-01T00:<b>00:59</b>.000.
   * <p>
   * Assertion fails as minute fields differ even if time difference is only 1ns.
   * <p>
   * Code example :
   *
   * <pre>
   * // successfull assertions
   * ZonedDateTime dateTime1 = ZonedDateTime.of(2000, 1, 1, 23, 50, 0, 0);
   * ZonedDateTime dateTime2 = ZonedDateTime.of(2000, 1, 1, 23, 50, 10, 456);
   * assertThat(dateTime1).isEqualToIgnoringSeconds(dateTime2);
   *
   * // failing assertions (even if time difference is only 1ns)
   * ZonedDateTime dateTimeA = ZonedDateTime.of(2000, 1, 1, 23, 50, 00, 0);
   * ZonedDateTime dateTimeB = ZonedDateTime.of(2000, 1, 1, 23, 49, 59, 999999999);
   * assertThat(dateTimeA).isEqualToIgnoringSeconds(dateTimeB);
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with second and nanosecond fields
   *           ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringSeconds(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringSeconds(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringSeconds(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month, day and hour fields (minute, second and
   * nanosecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link org.threeten.bp.ZoneId} before comparison.
   * <p>
   * Assertion can fail with dateTimes in same chronological second time window, e.g :
   * <p>
   * 2000-01-01T<b>01:00</b>:00.000 and 2000-01-01T<b>00:59:59</b>.000.
   * <p>
   * Time difference is only 1s but hour fields differ.
   * <p>
   * Code example :
   *
   * <pre>
   * // successfull assertions
   * ZonedDateTime dateTime1 = ZonedDateTime.of(2000, 1, 1, 23, 50, 0, 0);
   * ZonedDateTime dateTime2 = ZonedDateTime.of(2000, 1, 1, 23, 00, 2, 7);
   * assertThat(dateTime1).isEqualToIgnoringMinutes(dateTime2);
   *
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime dateTimeA = ZonedDateTime.of(2000, 1, 1, 01, 00, 00, 000);
   * ZonedDateTime dateTimeB = ZonedDateTime.of(2000, 1, 1, 00, 59, 59, 999);
   * assertThat(dateTimeA).isEqualToIgnoringMinutes(dateTimeB);
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal ignoring minute, second and nanosecond
   *           fields.
   */
  public ZonedDateTimeAssert isEqualToIgnoringMinutes(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!areEqualIgnoringMinutes(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringMinutes(actual, other));
    }
    return this;
  }

  /**
   * Verifies that actual and given {@code ZonedDateTime} have same year, month and day fields (hour, minute, second and
   * nanosecond fields are ignored in comparison).
   * <p>
   * Note that given {@link ZonedDateTime} is converted in the actual's {@link org.threeten.bp.ZoneId} before comparison.
   * <p>
   * Assertion can fail with dateTimes in same chronological minute time window, e.g :
   * <p>
   * 2000-01-<b>01T23:59</b>:00.000 and 2000-01-02T<b>00:00</b>:00.000.
   * <p>
   * Time difference is only 1min but day fields differ.
   * <p>
   * Code example :
   *
   * <pre>
   * // successfull assertions
   * ZonedDateTime dateTime1 = ZonedDateTime.of(2000, 1, 1, 23, 59, 59, 999);
   * ZonedDateTime dateTime2 = ZonedDateTime.of(2000, 1, 1, 00, 00, 00, 000);
   * assertThat(dateTime1).isEqualToIgnoringHours(dateTime2);
   *
   * // failing assertions (even if time difference is only 1ms)
   * ZonedDateTime dateTimeA = ZonedDateTime.of(2000, 1, 2, 00, 00, 00, 000);
   * ZonedDateTime dateTimeB = ZonedDateTime.of(2000, 1, 1, 23, 59, 59, 999);
   * assertThat(dateTimeA).isEqualToIgnoringHours(dateTimeB);
   * </pre>
   *
   * @param other the given {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if other {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is are not equal with second and nanosecond fields
   *           ignored.
   */
  public ZonedDateTimeAssert isEqualToIgnoringHours(ZonedDateTime other) {
    Objects.instance().assertNotNull(info, actual);
    assertDateTimeParameterIsNotNull(other);
    if (!haveSameYearMonthAndDayOfMonth(actual, other.withZoneSameInstant(actual.getZone()))) {
      throw Failures.instance().failure(info, shouldBeEqualIgnoringHours(actual, other));
    }
    return this;
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to the given one <b>in the actual's
   * {@link org.threeten.bp.ZoneId}</b>.
   * <p>
   * Example :
   *
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isEqualTo(ZonedDateTime.parse("2000-01-01T00:00:00Z"));
   * </pre>
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} in the actual
   *           ZonedDateTime's org.threeten.bp.ZoneId.
   */
  public ZonedDateTimeAssert isEqualTo(ZonedDateTime expected) {
    return super.isEqualTo(expected.withZoneSameInstant(actual.getZone()));
  }

  /**
   * Same assertion as {@link #isEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String,
   * which must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check.
   * <p>
   * Example :
   *
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isEqualTo("2000-01-01T00:00:00Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} built from
   *           given String.
   */
  public ZonedDateTimeAssert isEqualTo(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isEqualTo(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that the actual value is not equal to the given one <b>in the actual ZonedDateTime's org.threeten.bp.ZoneId</b>.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isNotEqualTo(ZonedDateTime.parse("2000-01-15T00:00:00Z"));
   * </pre>
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is equal to the {@link ZonedDateTime} in the actual
   *           ZonedDateTime's org.threeten.bp.ZoneId.
   */
  public ZonedDateTimeAssert isNotEqualTo(ZonedDateTime expected) {
    return super.isNotEqualTo(expected.withZoneSameInstant(actual.getZone()));
  }

  /**
   * Same assertion as {@link #isNotEqualTo(ZonedDateTime)} but the {@link ZonedDateTime} is built from given String,
   * which must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime} created from the given String is built in the {@link org.threeten.bp.ZoneId} of the
   * {@link ZonedDateTime} to check.. {@link ZonedDateTime}.
   * <p>
   * Example :
   *
   * <pre>
   * // use directly String in comparison to avoid a conversion
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isNotEqualTo("2000-01-15T00:00:00Z");
   * </pre>
   *
   * @param dateTimeAsString String representing a {@link ZonedDateTime}.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is equal to the {@link ZonedDateTime} built from given
   *           String.
   */
  public ZonedDateTimeAssert isNotEqualTo(String dateTimeAsString) {
    assertDateTimeAsStringParameterIsNotNull(dateTimeAsString);
    return isNotEqualTo(parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimeAsString));
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to one of the given {@link ZonedDateTime} <b>in the actual
   * ZonedDateTime's {@link org.threeten.bp.ZoneId}</b>.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z"))
   *           .isIn(ZonedDateTime.parse("1999-12-31T23:59:59Z"), ZonedDateTime.parse("2000-01-01T00:00:00Z"));
   * </pre>
   *
   * @param expected the given {@link ZonedDateTime}s to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the given {@link ZonedDateTime}s.
   */
  public ZonedDateTimeAssert isIn(ZonedDateTime... expected) {
    return super.isIn((Object[]) changeToActualTimeZone(expected));
  }

  /**
   * Same assertion as {@link #isIn(ZonedDateTime...)} but the {@link ZonedDateTime} are built from given String, which
   * must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime}s created from the given Strings are built in the {@link org.threeten.bp.ZoneId} of
   * the {@link ZonedDateTime} to check..
   * <p>
   * Example :
   *
   * <pre>
   * // use String based representation of LocalDateTime
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isIn("1999-12-31T23:59:59Z", "2000-01-01T00:00:00Z");
   * </pre>
   *
   * @param dateTimesAsString String array representing {@link ZonedDateTime}s.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the {@link ZonedDateTime}s built from given
   *           Strings.
   */
  public ZonedDateTimeAssert isIn(String... dateTimesAsString) {
    checkIsNotNullAndNotEmpty(dateTimesAsString);
    return isIn(convertToDateTimeArray(dateTimesAsString));
  }

  /**
   * Verifies that the actual {@link ZonedDateTime} is equal to one of the given {@link ZonedDateTime} <b>in the actual
   * ZonedDateTime's {@link org.threeten.bp.ZoneId}</b>.
   * <p>
   * Example :
   *
   * <pre>
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z"))
   *          .isNotIn(ZonedDateTime.parse("1999-12-31T23:59:59Z"), ZonedDateTime.parse("2000-01-02T00:00:00Z"));
   * </pre>
   *
   * @param expected the given {@link ZonedDateTime}s to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not in the given {@link ZonedDateTime}s.
   */
  public ZonedDateTimeAssert isNotIn(ZonedDateTime... expected) {
    return super.isNotIn((Object[]) changeToActualTimeZone(expected));
  }

  /**
   * Same assertion as {@link #isNotIn(ZonedDateTime...)} but the {@link ZonedDateTime} is built from given String,
   * which must follow <a
   * href="http://www.threeten.org/threetenbp/apidocs/org/threeten/bp/format/DateTimeFormatter.html#ISO_DATE_TIME"
   * >ISO date-time format</a> to allow calling {@link ZonedDateTime#parse(CharSequence, DateTimeFormatter)} method.
   * <p>
   * Note that the {@link ZonedDateTime}s created from the given Strings are built in the {@link org.threeten.bp.ZoneId} of
   * the {@link ZonedDateTime} to check..
   * <p>
   * Example :
   *
   * <pre>
   * // use String based representation of ZonedDateTime
   * assertThat(ZonedDateTime.parse("2000-01-01T00:00:00Z")).isNotIn("1999-12-31T23:59:59Z", "2000-01-02T00:00:00Z");
   * </pre>
   *
   * @param dateTimesAsString String array representing {@link ZonedDateTime}s.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code ZonedDateTime} is {@code null}.
   * @throws IllegalArgumentException if given String is null or can't be converted to a {@link ZonedDateTime}.
   * @throws AssertionError if the actual {@code ZonedDateTime} is not equal to the {@link ZonedDateTime} built from
   *            given String.
   */
  public ZonedDateTimeAssert isNotIn(String... dateTimesAsString) {
    checkIsNotNullAndNotEmpty(dateTimesAsString);
    return isNotIn(convertToDateTimeArray(dateTimesAsString));
  }

  private ZonedDateTime[] convertToDateTimeArray(String... dateTimesAsString) {
    ZonedDateTime[] dates = new ZonedDateTime[dateTimesAsString.length];
    for (int i = 0; i < dateTimesAsString.length; i++) {
      // building the ZonedDateTime in actual's ZoneId
      dates[i] = parseStringAsIsoDateTimeAndMoveToZoneSameActual(dateTimesAsString[i]);
    }
    return dates;
  }

  private ZonedDateTime[] changeToActualTimeZone(ZonedDateTime... dateTimes) {
    ZonedDateTime[] dates = new ZonedDateTime[dateTimes.length];
    for (int i = 0; i < dateTimes.length; i++) {
      // building the ZonedDateTime in actual's ZoneId
      dates[i] = dateTimes[i].withZoneSameInstant(actual.getZone());
    }
    return dates;
  }

  private void checkIsNotNullAndNotEmpty(Object[] values) {
    if (values == null) {
      throw new IllegalArgumentException("The given ZonedDateTime array should not be null");
    }
    if (values.length == 0) {
      throw new IllegalArgumentException("The given ZonedDateTime array should not be empty");
    }
  }

  private ZonedDateTime parseStringAsIsoDateTimeAndMoveToZoneSameActual(String dateTimeAsString) {
    ZonedDateTime parsedDateTime = ZonedDateTime.parse(dateTimeAsString, DateTimeFormatter.ISO_DATE_TIME);
    return parsedDateTime.withZoneSameInstant(actual.getZone());
  }

  /**
   * Check that the {@link ZonedDateTime} to compare actual {@link ZonedDateTime} to is not null, otherwise throws a
   * {@link IllegalArgumentException} with an explicit message
   *
   * @param dateTime the {@link ZonedDateTime} to check
   * @throws IllegalArgumentException with an explicit message if the given {@link ZonedDateTime} is null
   */
  private static void assertDateTimeParameterIsNotNull(ZonedDateTime dateTime) {
    if (dateTime == null) {
      throw new IllegalArgumentException(NULL_DATE_TIME_PARAMETER_MESSAGE);
    }
  }

  /**
   * Check that the {@link ZonedDateTime} string representation to compare actual {@link ZonedDateTime} to is not null,
   * otherwise throws a {@link IllegalArgumentException} with an explicit message
   *
   * @param dateTimeAsString String representing the ZonedDateTime to compare actual with
   * @throws IllegalArgumentException with an explicit message if the given {@link String} is null
   */
  private static void assertDateTimeAsStringParameterIsNotNull(String dateTimeAsString) {
    if (dateTimeAsString == null) {
      throw new IllegalArgumentException(
          "The String representing the ZonedDateTime to compare actual with should not be null");
    }
  }

  /**
   * Returns true if both datetime are in the same year, month and day of month, hour, minute and second, false
   * otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year, month and day of month, hour, minute and second, false
   *         otherwise.
   */
  private static boolean areEqualIgnoringNanos(ZonedDateTime actual, ZonedDateTime other) {
    return areEqualIgnoringSeconds(actual, other) && actual.getSecond() == other.getSecond();
  }

  /**
   * Returns true if both datetime are in the same year, month, day of month, hour and minute, false otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year, month, day of month, hour and minute, false otherwise.
   */
  private static boolean areEqualIgnoringSeconds(ZonedDateTime actual, ZonedDateTime other) {
    return areEqualIgnoringMinutes(actual, other) && actual.getMinute() == other.getMinute();
  }

  /**
   * Returns true if both datetime are in the same year, month, day of month and hour, false otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year, month, day of month and hour, false otherwise.
   */
  private static boolean areEqualIgnoringMinutes(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYearMonthAndDayOfMonth(actual, other) && actual.getHour() == other.getHour();
  }

  /**
   * Returns true if both datetime are in the same year, month and day of month, false otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year, month and day of month, false otherwise
   */
  private static boolean haveSameYearMonthAndDayOfMonth(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYearAndMonth(actual, other) && actual.getDayOfMonth() == other.getDayOfMonth();
  }

  /**
   * Returns true if both datetime are in the same year and month, false otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year and month, false otherwise
   */
  private static boolean haveSameYearAndMonth(ZonedDateTime actual, ZonedDateTime other) {
    return haveSameYear(actual, other) && actual.getMonth() == other.getMonth();
  }

  /**
   * Returns true if both datetime are in the same year, false otherwise.
   *
   * @param actual the actual datetime. expected not be null
   * @param other the other datetime. expected not be null
   * @return true if both datetime are in the same year, false otherwise
   */
  private static boolean haveSameYear(ZonedDateTime actual, ZonedDateTime other) {
    return actual.getYear() == other.getYear();
  }
}
