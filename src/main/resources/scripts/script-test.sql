INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, categoria_material, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion,fec_creacion,fec_actualizacion)
	VALUES ('JK2', false, 'asfasfasf', 'usu1', 'usu1', 'eq1', 'eq2', '2022-06-21T00:00:00', '2022-06-21T00:00:00');

INSERT INTO public.material(
	id, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial, usu_creacion, usu_actualizacion, equipo_creacion, equipo_actualizacion)
	VALUES (1,'2022-06-21T00:00:00', 'test', 'test', 'test', '2022-06-21T00:00:00', false, 4, 'test', 'JK2', 'usu1', 'usu1', 'eq1', 'eq2');