package com.example.complier;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return true;
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for(Class<? extends Annotation> annotation : supportAnnotations){
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations(){
        final Set<Class<? extends Annotation>> Annotations = new LinkedHashSet<>();
        Annotations.add(EntryGenerator.class);
        Annotations.add(PayEntryGenerator.class);
        Annotations.add(AppRegisterGenerator.class);
        return Annotations;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor){
        for(Element typeElement : env.getElementsAnnotatedWith(annotation)){
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for(AnnotationMirror annotationMirror : annotationMirrors){
                final Map<? extends ExecutableElement,? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for(Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry : elementValues.entrySet()){
                    entry.getValue().accept(visitor,null);
                }
            }
        }
    }


    private void generateEntryCode(RoundEnvironment env){
        final EntryVistor entryVistor = new EntryVistor();
        entryVistor.setFilder(processingEnv.getFiler());
        scan(env,EntryGenerator.class,entryVistor);
    }

    private void generatePayEntryCode(RoundEnvironment env){
        final PayEntryVistor payEntryVistor = new PayEntryVistor();
        payEntryVistor.setFilder(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,payEntryVistor);
    }


    private void generateAppRegisterCode(RoundEnvironment env){
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFilder(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,appRegisterVisitor);
    }


}
