package library.misc;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class ModelConverter {
	public static Model convertMeshToModel(final String id, final Mesh mesh) {
	      ModelBuilder builder = new ModelBuilder();
	      builder.begin();
	      builder.part(id, mesh, GL20.GL_TRIANGLES, new Material());
	      return builder.end();
	   }
}
