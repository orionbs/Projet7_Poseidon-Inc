package fr.orionbs.services;

import fr.orionbs.dataTransferObjects.RatingDTO;
import fr.orionbs.models.Rating;
import fr.orionbs.repositories.RatingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public boolean creatingRating(RatingDTO ratingDTO) {

        if (ratingDTO == null) {
            log.info("Rating is empty");
            return false;
        }

        log.info("Creating Rating, {}", ratingDTO);
        Rating rating = ratingDTO.ratingDtoToRating(ratingDTO);
        ratingRepository.save(rating);
        return true;
    }

    public RatingDTO readingRating(Integer index) {

        log.info("Reading Rating Id {}", index);
        Rating rating = ratingRepository.getById(index);
        return new RatingDTO().ratingToRatingDTO(rating);

    }

    public List<RatingDTO> readingAllRating() {

        log.info("Reading All Ratings");
        RatingDTO ratingDTO = RatingDTO.builder().build();
        return ratingDTO.ratingToRatingDTOList(ratingRepository.findAll());

    }

    public boolean updatingRating(RatingDTO ratingDTO) {

        if (ratingDTO == null) {
            log.info("Rating is empty");
            return false;
        }

        if (ratingRepository.findById(ratingDTO.getId()) == null) {
            log.info("Rating doesn't exist");
            return false;
        }

        Rating rating = ratingDTO.ratingDtoToRating(ratingDTO);
        log.info("Updating Rating, {}", ratingDTO);
        ratingRepository.save(rating);
        return true;

    }

    public boolean deletingRating(Integer index) {

        if (ratingRepository.findById(index) == null) {
            log.info("Rating doesn't exist");
            return false;
        }
        ratingRepository.deleteById(index);
        log.info("Deleting Rating {}", index);
        return true;

    }
}
