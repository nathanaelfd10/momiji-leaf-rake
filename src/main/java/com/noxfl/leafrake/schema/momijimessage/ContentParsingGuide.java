package com.noxfl.leafrake.schema.momijimessage;

import com.noxfl.momijitreehouse.model.Field;
import com.noxfl.momijitreehouse.crawler.ContentType;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ContentParsingGuide {

    private String source;
    private ContentType contentType;
    private List<Field> fields;

}
