/*
 * Changes to this file committed after and not including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * This file is part of Hopsworks
 * Copyright (C) 2018, Logical Clocks AB. All rights reserved
 *
 * Hopsworks is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Hopsworks is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 *
 * Changes to this file committed before and including commit-id: ccc0d2c5f9a5ac661e60e6eaf138de7889928b8b
 * are released under the following license:
 *
 * Copyright (C) 2013 - 2018, Logical Clocks AB and RISE SICS AB. All rights reserved
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 * persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS  OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.hops.hopsworks.common.jobs.adam;

/**
 * An optional argument to an ADAM command. I.e. an argument that is preceded by
 * a dash ('-') in the command.
 */
public final class AdamOption {

  private final String name;
  private final String description;
  private final boolean valueIsPath;
  private final boolean isFlag; //If is flag: no value required. If it is no flag, a value should be provided
  private final boolean isOutputPath;

  public AdamOption(String name, String description, boolean valueIsPath,
          boolean isFlag) {
    this(name, description, valueIsPath, isFlag, false);
  }

  public AdamOption(String name, String description, boolean valueIsPath,
          boolean isFlag, boolean isOutputPath) {
    if (isFlag && (valueIsPath || isOutputPath)) {
      throw new IllegalArgumentException(
              "An option cannot be both a path and a flag.");
    } else if (!valueIsPath && isOutputPath) {
      throw new IllegalArgumentException(
              "An option cannot be an output path but not a path.");
    }
    this.name = name;
    this.valueIsPath = valueIsPath;
    this.description = description;
    this.isFlag = isFlag;
    this.isOutputPath = isOutputPath;
  }

  public String getName() {
    return name;
  }

  public boolean isValuePath() {
    return valueIsPath;
  }

  public String getDescription() {
    return description;
  }

  public boolean isFlag() {
    return isFlag;
  }

  public boolean isOutputPath() {
    return isOutputPath;
  }

  public String getCliVal() {
    return "-" + name;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append(name).append("\t:\t");
    ret.append(description);
    if (valueIsPath) {
      ret.append("[PATH]");
    }
    if (isFlag) {
      ret.append("[FLAG]");
    }
    return ret.toString();
  }

}
