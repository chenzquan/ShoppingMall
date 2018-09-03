package com.example.complier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;


public final class EntryVistor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFilder = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;

    public void setFilder(Filer mFilder) {
        this.mFilder = mFilder;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        this.mTypeMirror = typeMirror;
        generateJavaCode();
        return aVoid;
    }


    private void generateJavaCode() {
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXEntryActivity")
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.FINAL)
                        .superclass(TypeName.get(mTypeMirror))
                        .build();


        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();


        try {
            javaFile.writeTo(mFilder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
