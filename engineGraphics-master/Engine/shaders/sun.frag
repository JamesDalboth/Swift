#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

const vec2 sun = vec2(0.5,0.5);

void main()
{
  float dist = distance(sun, fs_in.tc);
  if (dist < 0.5) {
    	color = vec4( 255.0f/250.0f, 252.0f/250.0f, 160.0f/255.0f ,1.0f);
  } else {
  	color = vec4(0.0f);
  }

}