/*
 * The MIT License
 *
 * Copyright (c) 2016, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package org.jenkinsci.plugins.pipeline.modeldefinition.ast;

import net.sf.json.JSONObject;
import org.jenkinsci.plugins.pipeline.modeldefinition.validator.ModelValidator;

/**
 * Represents a block for when/if a {@link ModelASTStage} will be executed or not.
 */
public class ModelASTWhen extends ModelASTScriptBlock { //Model wise we don't really extend it but there are plenty of reuse
    public ModelASTWhen(Object sourceLocation) {
        super(sourceLocation, "when");
    }

    @Override
    public String toString() {
        return "ModelASTWhen{" +
                "name='" + getName() + '\'' +
                ", args=" + getArgs() +
                "}";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        if (getArgs() != null) {
            o.accumulate("arguments", getArgs().toJSON());
        }
        return o;
    }

    @Override
    public void validate(ModelValidator validator) {
        super.validate(validator);
        validator.validateElement(this);
    }
}