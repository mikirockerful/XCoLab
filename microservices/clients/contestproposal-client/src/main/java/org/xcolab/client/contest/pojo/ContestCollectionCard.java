package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContestCollectionCard extends AbstractContestCollectionCard implements Serializable {

    public static final TypeProvider<ContestCollectionCard> TYPES =
            new TypeProvider<>(ContestCollectionCard.class,
                    new ParameterizedTypeReference<List<ContestCollectionCard>>() {});

    public ContestCollectionCard() {}

    public ContestCollectionCard(ContestCollectionCard value) {
        super(value);
    }

    public ContestCollectionCard(AbstractContestCollectionCard abstractContestCollectionCard) {
        super(abstractContestCollectionCard);
    }
}
