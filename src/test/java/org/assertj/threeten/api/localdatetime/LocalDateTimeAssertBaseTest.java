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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.threeten.api.localdatetime;

import org.assertj.threeten.api.LocalDateTimeAssert;
import org.assertj.threeten.api.ThreeTenBaseTest;
import org.junit.experimental.theories.DataPoint;
import org.threeten.bp.LocalDateTime;

import static org.junit.Assume.assumeTrue;


/**
 * 
 * Base test class for {@link LocalDateTimeAssert} tests.
 * 
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class LocalDateTimeAssertBaseTest extends ThreeTenBaseTest {

  @DataPoint
  public static LocalDateTime localDateTime1 = LocalDateTime.of(2000, 12, 14, 0, 0);
  @DataPoint
  public static LocalDateTime localDateTime2 = LocalDateTime.of(2000, 12, 13, 23, 59, 59, 999);
  @DataPoint
  public static LocalDateTime localDateTime3 = LocalDateTime.of(2000, 12, 14, 0, 0, 0, 1);
  @DataPoint
  public static LocalDateTime localDateTime4 = LocalDateTime.of(2000, 12, 14, 22, 15, 15, 875);
  @DataPoint
  public static LocalDateTime localDateTime5 = LocalDateTime.of(2000, 12, 14, 22, 15, 15, 874);
  @DataPoint
  public static LocalDateTime localDateTime6 = LocalDateTime.of(2000, 12, 14, 22, 15, 15, 876);

  protected static void testAssumptions(LocalDateTime reference, LocalDateTime dateBefore, LocalDateTime dateAfter) {
    assumeTrue(dateBefore.isBefore(reference));
    assumeTrue(dateAfter.isAfter(reference));
  }

}