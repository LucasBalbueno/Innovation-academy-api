package Innovation.Academy.Innovation_academy_api.service;

import Innovation.Academy.Innovation_academy_api.dto.TeacherDTO;
import Innovation.Academy.Innovation_academy_api.entities.TeacherEntity;
import Innovation.Academy.Innovation_academy_api.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeacher() {
        return teacherRepository
                .findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Integer id) {
        Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findById(id);
        return teacherEntityOptional.map(this::convertToDTO).orElse(null);
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setTeacherName(teacherDTO.getTeacherName());
        teacherEntity.setTeacherBiography(teacherDTO.getTeacherBiography());
        teacherEntity.setTeacherImage(teacherDTO.getTeacherImage());

        teacherRepository.save(teacherEntity);

        return convertToDTO(teacherEntity);
    }

    public TeacherDTO updateTeacher(Integer id, TeacherDTO teacherDTO) {
        Optional<TeacherEntity> teacherEntityOptional = teacherRepository.findById(id);

        if(teacherEntityOptional.isPresent()) {
            TeacherEntity teacherEntity = teacherEntityOptional.get();
            teacherEntity.setTeacherName(teacherDTO.getTeacherName());
            teacherEntity.setTeacherBiography(teacherDTO.getTeacherBiography());
            teacherEntity.setTeacherImage(teacherDTO.getTeacherImage());

            teacherRepository.save(teacherEntity);

            return convertToDTO(teacherEntity);
        }

        return null;
    }

    public void deleteTeacher(Integer id) {
        teacherRepository.deleteById(id);
    }

    private TeacherDTO convertToDTO(TeacherEntity teacherEntity) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacherEntity.getTeacherId());
        teacherDTO.setTeacherName(teacherEntity.getTeacherName());
        teacherDTO.setTeacherBiography(teacherEntity.getTeacherBiography());
        teacherDTO.setTeacherImage(teacherEntity.getTeacherImage());

        return teacherDTO;
    }
}