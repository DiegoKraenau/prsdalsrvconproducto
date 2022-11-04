INSERT INTO public.categoria_material(
	cod_catmaterial, flg_anulado, fec_creacion, categoria_material)
	VALUES ('JK2', false, '2022-06-21', 'asfasfasf');

INSERT INTO public.material(
	id, fec_creacion, nombre_largo, presentacion, nombre_corto, fec_actualizacion, flg_anulado, dias_vencimiento, tipo_vencimiento, cod_catmaterial)
	VALUES (1,'2022-06-21', 'test', 'test', 'test', '2022-06-21', false, 4, 'test', 'JK2');