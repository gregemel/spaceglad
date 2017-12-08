package com.emelwerx.world.services.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.emelwerx.world.databags.components.ModelComponent;

import java.util.Locale;

import static java.lang.String.format;

public class ModelComponentFactory {

    public static ModelComponent create(Model model, float x, float y, float z) {
        Gdx.app.log("ModelComponentFactory", format(Locale.US, "init model component %s, %f, %f, %f", model.toString(), x, y, z));
        ModelComponent modelComponent = new ModelComponent();
        Matrix4 matrix4 = new Matrix4();
        modelComponent.setMatrix4(matrix4);
        modelComponent.setModel(model);
        modelComponent.setPosition(new Vector3(x, y, z));
        ModelInstance modelInstance = new ModelInstance(model, matrix4.setToTranslation(x, y, z));
        modelComponent.setInstance(modelInstance);
        return modelComponent;
    }
}
