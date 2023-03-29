package com.noxfl.leafrake.schema.momijimessage;

import com.noxfl.leafrake.scraper.ContentType;
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
