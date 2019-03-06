package anh.tuan.craweler.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String title;
    private String detailUrl;
    private String mainImageUrl;
    private String content;
}
