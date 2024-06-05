package qnelldo.sixtimes.domain.post.mongodb.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import qnelldo.sixtimes.domain.post.mongodb.document.PostDocument;


public interface PostDocumentRepository extends MongoRepository<PostDocument, String> {

}