-- Actividades
INSERT INTO actividades (id, nombre_actividad, descripcion, duracion_minutos)
VALUES (1, 'Yoga', 'Clase de relajación y flexibilidad', 45),
       (2, 'CrossFit', 'Entrenamiento funcional de alta intensidad', 45),
       (3, 'Spinning', 'Ejercicio cardiovascular en bicicleta estática', 45),
       (4, 'Pilates', 'Fortalecimiento del core y postura', 45),
       (5, 'Zumba', 'Baile aeróbico divertido', 45);

-- Socios
INSERT INTO socios (id, nombre_completo, fecha_nacimiento, numero_socio)
VALUES (1, 'Ana García', '1985-03-15', 'SOC001'),
       (2, 'Carlos Ruiz', '1990-07-22', 'SOC002'),
       (3, 'María López', '1988-11-08', 'SOC003'),
       (4, 'Diego Morales', '1992-05-14', 'SOC004'),
       (5, 'Laura Martín', '1987-09-30', 'SOC005');

-- Instructores
INSERT INTO instructores (id, nombre_completo, especialidad, certificacion)
VALUES (1, 'Laura Fernández', 'Yoga', 'Instructor Certificado Yoga Alliance'),
       (2, 'Roberto Silva', 'CrossFit', 'CrossFit Level 2 Trainer'),
       (3, 'Sandra Pérez', 'Spinning', 'Certificación Spinning ACSM'),
       (4, 'Miguel Torres', 'Pilates', 'Pilates Method Alliance Certified'),
       (5, 'Carmen Vega', 'Zumba', 'Zumba Instructor License');

