package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.EBookType;
import com.bilgeadam.repository.enums.EStatus;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQueries(
        {
         @NamedQuery( name="findByBookType" ,query = "select b from Book as b where b.bookType=:myType"),
         @NamedQuery(name = "findbyId" ,query ="select b from Book as b where b.id=:myId" ),
         @NamedQuery(name="countBooktype" ,query ="select b.bookType,count(*) from Book b group by  bookType" )
        }
)

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private EBookType bookType;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status=EStatus.AVAILABLE;
    private int pageCount;
    @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "my_author_id",referencedColumnName = "id",nullable = false)
   // @ToString.Exclude
    private Author author;

}
