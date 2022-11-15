INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, categoria_material, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion,fec_creacion,fec_actualizacion)
	VALUES ('001', false, 'LECHE PASTEURIZADA', 'usu1', 'usu1', 'eq1', 'eq2', '2022-06-21T00:00:00', '2022-06-21T00:00:00');

INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, categoria_material, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion,fec_creacion,fec_actualizacion)
	VALUES ('002', false, 'QUESO PASTEURIZADO', 'usu1', 'usu1', 'eq1', 'eq2', '2022-06-21T00:00:00', '2022-06-21T00:00:00');


INSERT INTO public.material(
	id_material, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion)
	VALUES (1,'2022-06-21T00:00:00', 'LECHE TARRO', 'LEC TAR', 'LT', '2022-06-21T00:00:00', false, 4, 'TV1', '001', 'usu1', 'usu1', 'eq1', 'eq2');


INSERT INTO public.material(
	id_material, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion)
	VALUES (2,'2022-06-21T00:00:00', 'QUESO EMPAQUETADO', 'QUE EMP', 'QE', '2022-06-21T00:00:00', false, 4, 'TV1', '002', 'usu1', 'usu1', 'eq1', 'eq2');

INSERT INTO public.centro(
	id_centro, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion, flg_anulado, centro, id_pais, id_departamento, latitud, longitud)
	VALUES ('001', '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1', false, 'CENTRO 1', 'PER', 2, 50, 50);

INSERT INTO public.centro(
	id_centro, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion, flg_anulado, centro, id_pais, id_departamento, latitud, longitud)
	VALUES ('002', '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1', false, 'CENTRO 2', 'PER', 2, 50, 50);


INSERT INTO public.centro_material(
	id_centro, id_material, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion)
	VALUES ('001', 1, '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1');


INSERT INTO public.centro_material(
	id_centro, id_material, fec_creacion, equipo_creacion, equipo_actualizacion, fec_actualizacion, usu_creacion, usu_actualizacion)
	VALUES ('002', 2, '2022-06-21T00:00:00', 'eq1', 'eq1', '2022-06-21T00:00:00', 'usu1', 'usu1');	